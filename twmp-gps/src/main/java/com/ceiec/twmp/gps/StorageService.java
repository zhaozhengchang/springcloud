package com.ceiec.twmp.gps;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.common.dict.OnlineStatus;
import com.ceiec.twmp.tmp.common.dict.OpeStatus;
import com.ceiec.twmp.tmp.common.dict.TaskStatus;
import com.ceiec.twmp.tmp.common.enums.EKafkaGroup;
import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import com.ceiec.twmp.tmp.kafka.ConsumerFactory;
import com.ceiec.twmp.tmp.kafka.KafkaFactory;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.utils.tools.mongodb.MongodbUtils;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Ding
 * @version V1.0
 * @Description: save gps impl
 * @create 2019-03-20 11:57
 **/
public class StorageService {

    private static boolean mgConnectionFlag = false; //mongodb connection flag

    private static int cacheMaxCount = 5000; // if the num of gps is more than cacheMaxCount, will save gps into mongodb right now

    private static int period = 1000; // per xx , will save gps into mongodb

    private final static AtomicInteger CURRENT_NUM = new AtomicInteger(0); // the num of gps in cache

    private static List<WriteModel<Document>> documents = new ArrayList<>(); // gps documents cache

    private static Lock lock = new ReentrantLock();

    private static final Logger logger = LoggerFactory.getLogger(StorageService.class);

    private static ExecutorService executorService = new ThreadPoolExecutor(10,10,30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10, true),
            new ThreadPoolExecutor.CallerRunsPolicy());


    public static void start(){
        executorService.execute(new SaveGpsThread());  //save thread
        executorService.execute(new TestMongodbConnectionThread());//test mongodb connect


        KafkaConsumer consumer = KafkaFactory.getKafkaConsumer(EKafkaGroup.GROUP_GPS_SAVE.toString());
        ConsumerFactory.setConsumerTopic(consumer, EKafkaTopic.TOPIC_GPS);

        while(true){
            while(mgConnectionFlag){
                try{
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records){
                        GpsInfo gpsInfo = JSON.parseObject(record.value(), GpsInfo.class);
                        saveMessage(gpsInfo);
                    }

                }catch (Exception e){
                    logger.error("consumer get records is failed",e);
                }
            }

            if(!mgConnectionFlag){ // wait for connect mongodb
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
            }
        }

    }


    /*************************************************************************************************************************************
     ** @Description  testing mongodb connection
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/19 15:41
     **
     ************************************************************************************************************************************/
    private static class TestMongodbConnectionThread extends Thread{
        @Override
        public void run(){
            while(true){
                mgConnectionFlag = MongodbUtils.testConnection();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {}
            }
        }
    }


    /*************************************************************************************************************************************
     ** @Description save gps into mongodb thread
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/20 14:11
     **
     ************************************************************************************************************************************/
    private static class SaveGpsThread extends Thread{
        @Override
        public void run(){
            while (true){
                try {
                    Thread.sleep(period);
                } catch (InterruptedException e) { }

                saveToDB();
            }
        }
    }


    /*************************************************************************************************************************************
     ** @Description save gps into redis and convert gps to mongodb document in cache
     ** @param: gpsInfo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/19 17:10
     **
     ************************************************************************************************************************************/
    private static void saveMessage(GpsInfo gpsInfo) {
        RedisDeviceInfoVO redisDeviceInfoVO = DeviceInfoRedis.getDevice(gpsInfo.getDeviceNumber());
        if(redisDeviceInfoVO == null){
            return;
        }

        //if device status is not right , do not save gps
        if(redisDeviceInfoVO.getOpeStatus() != OpeStatus.installed.value){
            return;
        }

        //if device of the task status is not right , do not save gps
        if(redisDeviceInfoVO.getTaskStatus() != TaskStatus.monitoring.value){
            return;
        }


        redisDeviceInfoVO.setOnlineFlag(OnlineStatus.online.value);
        redisDeviceInfoVO.setLatitude(gpsInfo.getLatitude());
        redisDeviceInfoVO.setLongitude(gpsInfo.getLongitude());
        redisDeviceInfoVO.setDirection(gpsInfo.getLatitude());
        redisDeviceInfoVO.setSpeed(gpsInfo.getSpeed());
        redisDeviceInfoVO.setGpsTime(gpsInfo.getGpsTime());

        DeviceInfoRedis.saveDevice(redisDeviceInfoVO);

        gpsInfo.setTaskCode(redisDeviceInfoVO.getTaskCode());
        lock.lock();
        try{
            documents.add(new InsertOneModel<Document>(gpsInfo.convertGpsToDocument()));
        }catch (Exception e){
            logger.error("update new gps to document is failed",e);
        }finally {
            lock.unlock();
        }

        if(CURRENT_NUM.incrementAndGet()>cacheMaxCount){
            saveToDB();
        }
    }


    /*************************************************************************************************************************************
     ** @Description save gps into mongodb impl
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/20 14:18
     **
     ************************************************************************************************************************************/
    private static void saveToDB(){
        if(CURRENT_NUM.get() > 0 ){
            lock.lock();
            try{
                final List<WriteModel<Document>> documentsCopy = new ArrayList<>(documents);

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        MongodbUtils.executeInsert(documentsCopy);
                    }
                };
                //clear data and current num
                documents.clear();
                CURRENT_NUM.getAndSet(0);
            }catch (Exception e){
                logger.error("copy cache document is failed",e);
            }finally {
                lock.unlock();
            }
        }
    }
}
