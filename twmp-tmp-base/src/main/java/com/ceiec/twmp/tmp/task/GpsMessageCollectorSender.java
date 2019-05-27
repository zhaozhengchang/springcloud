package com.ceiec.twmp.tmp.task;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.twmp.tmp.common.dict.OpeStatus;
import com.ceiec.twmp.tmp.common.enums.EKafkaGroup;
import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import com.ceiec.twmp.tmp.kafka.ConsumerFactory;
import com.ceiec.twmp.tmp.kafka.KafkaFactory;
import com.ceiec.twmp.tmp.websocket.message.MessageSender;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.cache.redis.UserSubscriptionRedis;
import com.ceiec.twmp.tmp.utils.tools.ServerIdUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserSubscriptionVO;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps message push to webscokect
 * @create 2019-03-15 10:57
 **/
@Component
public class GpsMessageCollectorSender {
    private static Logger logger = LoggerFactory.getLogger(GpsMessageCollectorSender.class);

    private static KafkaConsumer consumer;

    private static long period = 1*1000; //send gps per period

    private static Lock gpsQueueLock = new ReentrantLock();

    private static Lock userDeviceLock = new ReentrantLock();

    private static final long CORRECT_TIME = 60*60* 1000L; //time fixed ,

    private static BlockingQueue<GpsInfo> gpsQueue = new LinkedBlockingDeque<>();

    private static ConcurrentHashMap<Long, List<GpsInfo>> userGps = new ConcurrentHashMap<>();

    private static Map<Long, Set<String>> userDeviceMap = new HashMap<>();//user subscription device number

    private static ExecutorService executorService = new ThreadPoolExecutor(10,10,30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10, true),
            new ThreadPoolExecutor.CallerRunsPolicy());

    @Autowired
    private MessageSender messageSender;



    /*************************************************************************************************************************************
     ** @Description init kafka consumer factory
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/15 11:05
     **
     ************************************************************************************************************************************/
    private void init(){

        consumer = KafkaFactory.getKafkaConsumer(EKafkaGroup.GROUP_GPS_SEND + ServerIdUtils.getServiceId());
        ConsumerFactory.setConsumerTopic(consumer, EKafkaTopic.TOPIC_GPS);

        executorService.execute(new GetGps());
        executorService.execute(new ClassifyGps());
        executorService.execute(new SendGPS());
        executorService.execute(new GetUserDevice());

    }


    /*************************************************************************************************************************************
     ** @Description  get gps from kafka
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/15 11:05
     **
     ************************************************************************************************************************************/
    private class GetGps extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records)
                    {
                        GpsInfo gpsInfo = (GpsInfo) JSONObject.parse(record.value());

                        if((System.currentTimeMillis()- DateFormatUtils.stringToDate(gpsInfo.getGpsTime(), DateFormatUtils.DATETIME_FORMAT_SYSTEM).getTime())>CORRECT_TIME){
                            //if get some gps and the gps time over now than CORRECT_TIME, then no push
                            continue;
                        }


                        RedisDeviceInfoVO redisDeviceInfoVO = DeviceInfoRedis.getDevice(gpsInfo.getDeviceNumber());
                        if(redisDeviceInfoVO == null || redisDeviceInfoVO.getOpeStatus() != OpeStatus.installed.value){
                            //if device info is null or device status is installing , ignore the gps info
                            continue;
                        }
                        gpsQueue.add(gpsInfo);
                    }
                }catch (Exception e){
                    logger.error("get gps info is failed", e);
                }
            }
        }
    }

    /*************************************************************************************************************************************
     ** @Description get user subscription , the user-deviceId map
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/15 15:50
     **
     ************************************************************************************************************************************/
    private class GetUserDevice extends Thread{
        @Override
        public void run(){
            while (true){
                try{
                    userDeviceLock.lock();

                    Map<String, RedisUserOnlineVO> userOnlineVOMap = UserInfoRedis.getUserOnline();
                    if(userOnlineVOMap!=null && userOnlineVOMap.size()>0){
                        for(String key: userOnlineVOMap.keySet()){
                            RedisUserOnlineVO redisUserOnlineVO = userOnlineVOMap.get(key);
                            if(redisUserOnlineVO.getServerId().equals(ServerIdUtils.getServiceId())){
                                RedisUserSubscriptionVO redisUserSubscriptionVO = UserSubscriptionRedis.getUserSubscription(redisUserOnlineVO.getUserId());
                                userDeviceMap.put(redisUserSubscriptionVO.getUserId(), redisUserSubscriptionVO.getDeviceNumber());
                            }
                        }
                    }

                    userDeviceLock.unlock();

                    sleep(1000);//ever 1 second, flush the user-device map

                }catch (Exception e){
                    logger.error("getUserDeviceMap is failed", e);
                    userDeviceLock.lock();
                }
            }
        }
    }



    /*************************************************************************************************************************************
     ** @Description classify gps into user group
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/15 11:32
     **
     ************************************************************************************************************************************/
    private class ClassifyGps extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    while(gpsQueue!=null && gpsQueue.size()>0){
                        gpsQueueLock.lock();
                        userDeviceLock.lock();

                        GpsInfo gpsInfo = gpsQueue.poll();//get gps from queue

                        if(userDeviceMap!=null && userDeviceMap.size()>0){//make sure gps in user subscription and classify gps into user group
                            for(Long userId: userDeviceMap.keySet()){
                                if(userDeviceMap.get(userId).contains(gpsInfo.getDeviceNumber())){
                                    List<GpsInfo> gpsInfoList = userGps.get(userId);
                                    if(gpsInfoList==null){
                                        gpsInfoList = new ArrayList<>();
                                    }
                                    gpsInfoList.add(gpsInfo);
                                    userGps.put(userId, gpsInfoList);
                                }
                            }
                        }

                        userDeviceLock.unlock();
                        gpsQueueLock.unlock();
                    }

                    //ever 100 millis get gps info from queue
                    sleep(100);

                }catch (Exception e){
                   logger.warn("classify gps into user group is error",e);
                    userDeviceLock.unlock();
                    gpsQueueLock.unlock();
                }

            }
        }
    }

    /*************************************************************************************************************************************
     ** @Description send gps
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/15 16:40
     **
     ************************************************************************************************************************************/
    private class SendGPS extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    if(userGps !=null && userGps.size()>0){
                        gpsQueueLock.lock();

                        for (Long userId : userGps.keySet()) {
                            List<GpsInfo> gpsList = userGps.get(userId);
                            if(gpsList!=null && gpsList.size()>0){
                                messageSender.sendGpsListMessageImpl(userId, gpsList);
                                userGps.get(userId).clear(); //after send, clear gps cache
                            }
                        }
                        gpsQueueLock.unlock();
                    }
                    sleep(period);
                }catch (Exception e){
                    logger.error("send gps message is failed",e);
                    gpsQueueLock.unlock();
                }
            }
        }
    }


}
