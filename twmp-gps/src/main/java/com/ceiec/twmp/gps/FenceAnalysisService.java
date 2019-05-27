package com.ceiec.twmp.gps;

import com.alibaba.fastjson.JSON;
import com.ceiec.twmp.tmp.cache.redis.AlarmNumberRedis;
import com.ceiec.twmp.tmp.cache.redis.DeviceInfoRedis;
import com.ceiec.twmp.tmp.common.dict.*;
import com.ceiec.twmp.tmp.common.enums.EKafkaGroup;
import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf;
import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.kafka.ConsumerFactory;
import com.ceiec.twmp.tmp.kafka.KafkaFactory;
import com.ceiec.twmp.tmp.kafka.ProducerFactory;
import com.ceiec.twmp.tmp.services.IAlarmService;
import com.ceiec.twmp.tmp.services.IMessageService;
import com.ceiec.twmp.tmp.utils.SpringUtil;
import com.ceiec.twmp.tmp.utils.message.LocaleMessageSourceService;
import com.ceiec.twmp.tmp.utils.tools.GisTools;
import com.ceiec.twmp.tmp.utils.tools.TemplateConvertUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;
import com.ceiec.twmp.tmp.utils.tools.snowflake.SnowflakeIdWorkerUtil;
import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;
import com.ceiec.twmp.tmp.vo.fence.FenceShapeVO;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: analysis gps in/out fence
 * @create 2019-03-20 13:54
 **/
public class FenceAnalysisService {

    private static final Logger logger = LoggerFactory.getLogger(FenceAnalysisService.class);


    private static int brokenTimes = 2; //if the same device break the same fence XX times , then it will generate a fence alarm
    private static Map<String, Map<Long,Integer>> deviceFenceAlarmNum = new HashMap<>();
//    private static List<TwmpSmAlarmEf> alarmEfList = new ArrayList<>();
//    private static Lock lock = new ReentrantLock();

    public static void start(){

        KafkaConsumer consumer = KafkaFactory.getKafkaConsumer(EKafkaGroup.GROUP_GPS_ANALYSIS.toString());
        ConsumerFactory.setConsumerTopic(consumer, EKafkaTopic.TOPIC_GPS);

        while(true){
            try{
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records){
                    GpsInfo gpsInfo = JSON.parseObject(record.value(), GpsInfo.class);
                    analysis(gpsInfo);


                }

            }catch (Exception e){
                logger.error("consumer get records is failed",e);
            }

        }

    }


    /*************************************************************************************************************************************
     ** @Description analysis gps info , if fence rule is broken, save alarm into db
     ** @param: gpsInfo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/21 11:37
     **
     ************************************************************************************************************************************/
    public static void analysis(GpsInfo gpsInfo){
        TwmpSmAlarmEf twmpSmAlarmEf = new TwmpSmAlarmEf();

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

        List<FenceShapeVO> fenceShapeList = redisDeviceInfoVO.getFenceShapeList();
        if(fenceShapeList!=null&& fenceShapeList.size()>0){
            for(FenceShapeVO fenceShapeVO: fenceShapeList){

                try{
                    //if gps time in fence time
                    Date beginTime = DateFormatUtils.stringToDate(fenceShapeVO.getStartTime(), DateFormatUtils.FENCE_TIME);
                    Date endTime = DateFormatUtils.stringToDate(fenceShapeVO.getStartTime(), DateFormatUtils.FENCE_TIME);

                    if(!DateUtils.compareHourMin(beginTime, endTime, DateFormatUtils.stringToDate(gpsInfo.getGpsTime(), DateFormatUtils.DATETIME_FORMAT_SYSTEM))){
                        continue;
                    }

                    //if gps break fence rule
                    boolean breakFenceRule =  (GisTools.gpsInPoly(fenceShapeVO.getWktPoly(), gpsInfo.getLongitude(), gpsInfo.getLatitude()) && fenceShapeVO.getFenceType()==FenceType.noEntrance.value)
                            ||(!(GisTools.gpsInPoly(fenceShapeVO.getWktPoly(), gpsInfo.getLongitude(), gpsInfo.getLatitude())) && fenceShapeVO.getFenceType() == FenceType.noGoOut.value);
                    if(breakFenceRule){


                        if(fenceShapeVO.isFlag()){ ////switch on/off the gps drift calculation
                            boolean flag = gpsDriftCalculation(gpsInfo, fenceShapeVO);
                            if(flag){
                                twmpSmAlarmEf = addAlarmCache(gpsInfo, redisDeviceInfoVO);
                            }
                        }else{
                            twmpSmAlarmEf = addAlarmCache(gpsInfo, redisDeviceInfoVO);
                        }


                        //to save and send alarm message
                        TwmpSysMessage message = saveMessage(twmpSmAlarmEf, twmpSmAlarmEf);


                        ProducerFactory producerFactory = KafkaFactory.getKafkaProducer();
                        producerFactory.sendMsg(EKafkaTopic.TOPIC_ALARM_MESSAGE, JSON.toJSONString(message));


                    }
                }catch (Exception e){
                    logger.error("read gps,fence info is error", e);
                }

            }
        }
    }

    /*************************************************************************************************************************************
     ** @Description save system message
     ** @param: twmpSmAlarmEf
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/27 17:11
     **
     ************************************************************************************************************************************/
    private static TwmpSysMessage saveMessage(TwmpSmAlarmEf twmpSmAlarmEf, TwmpSmAlarmEf alarmEf){
        TwmpSysMessage message = new TwmpSysMessage();
        message.setMessageId(SnowflakeIdWorkerUtil.generateLongId());
        message.setBusinessId(twmpSmAlarmEf.getAlarmId());
        message.setMessageType(MessageType.alarm.value);
        message.setMessageSubType(MessageSubType.overFence.value);


        LocaleMessageSourceService localeMessageSourceService = SpringUtil.getBean("LocaleMessageSourceService", LocaleMessageSourceService.class);
        String template = localeMessageSourceService.getMessageLocal(MessageSubType.overFence.comment, null);
        String comment =TemplateConvertUtils.getValueJson(template, alarmEf);
        message.setMessageComment(comment);
        message.setDepartmentId(twmpSmAlarmEf.getDepartmentId());
        message.setMessageTime(twmpSmAlarmEf.getAlarmTime());

        IMessageService messageService = SpringUtil.getBean("IMessageService", IMessageService.class);
        messageService.saveMessage(message);

        return message;
    }

//    /*************************************************************************************************************************************
//     ** @Description save alarm in database thread
//     ** @Return
//     ** @Author Ding
//     ** @Date 2019/3/22 11:39
//     **
//     ************************************************************************************************************************************/
//    private static class saveAlarmThread extends Thread{
//        @Override
//        public void run(){
//            while(true){
//
//
//
//
//            }
//        }
//    }


    /*************************************************************************************************************************************
     ** @Description update alarm into cache
     ** @param: gpsInfo
     ** @param: redisDeviceInfoVO
     ** @param: fenceShapeVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/21 15:43
     **
     ************************************************************************************************************************************/
    private static TwmpSmAlarmEf addAlarmCache(GpsInfo gpsInfo, RedisDeviceInfoVO redisDeviceInfoVO){
        TwmpSmAlarmEf alarmEf = new TwmpSmAlarmEf();
        alarmEf.setAlarmId(SnowflakeIdWorkerUtil.generateLongId());
        alarmEf.setAlarmNumber(AlarmNumberRedis.generateAlarmNumber());
        alarmEf.setTaskId(redisDeviceInfoVO.getTaskId());
        alarmEf.setTaskCode(redisDeviceInfoVO.getTaskCode());
        alarmEf.setTaskLevel(redisDeviceInfoVO.getTaskLevel());
        alarmEf.setDeviceNumber(redisDeviceInfoVO.getDeviceNumber());
        alarmEf.setAlarmType(AlarmType.overFence.value);
        alarmEf.setAlarmTime(new Date());
        alarmEf.setAlarmStatus(AlarmStatus.undistributed.value);
        alarmEf.setDepartmentId(redisDeviceInfoVO.getDepartmentId());
        alarmEf.setLatitude(gpsInfo.getLatitude());
        alarmEf.setLongitude(gpsInfo.getLongitude());
        alarmEf.setSpeed(gpsInfo.getSpeed());
        alarmEf.setDirection(gpsInfo.getDirection());
        alarmEf.setGpsTime(DateFormatUtils.stringToDate(gpsInfo.getGpsTime(), DateFormatUtils.DATETIME_FORMAT_SYSTEM));
        alarmEf.setCreator("system");
        alarmEf.setCreatorId(0L);
        alarmEf.setCreateTime(new Date());
        alarmEf.setPersonId(redisDeviceInfoVO.getPersonId());
        alarmEf.setDeviceId(redisDeviceInfoVO.getDeviceId());
        alarmEf.setPersonName(redisDeviceInfoVO.getPersonName());

        IAlarmService alarmService = SpringUtil.getBean("IAlarmService", IAlarmService.class);
        alarmService.saveAlarm(alarmEf);
        return alarmEf;
//        lock.lock();
//        try{
//            alarmEfList.update(alarmEf);
//        }catch (Exception e){
//            logger.error("update alarm into cache is error",e);
//        }finally {
//            lock.unlock();
//        }
    }


    /*************************************************************************************************************************************
     ** @Description in case gps drift
     ** @param: gpsInfo
     ** @param: redisDeviceInfoVO
     ** @param: fenceShapeVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/21 15:41
     **
     ************************************************************************************************************************************/
    private static boolean gpsDriftCalculation(GpsInfo gpsInfo, FenceShapeVO fenceShapeVO){
        if(deviceFenceAlarmNum.get(gpsInfo.getDeviceNumber())!=null){
            Map<Long, Integer> fenceAlarmNum = deviceFenceAlarmNum.get(gpsInfo.getDeviceNumber());
            int num = fenceAlarmNum.get(fenceShapeVO.getFenceId())+1;
            if(num>=brokenTimes){
                fenceAlarmNum.put(fenceShapeVO.getFenceId(), 0);
                deviceFenceAlarmNum.put(gpsInfo.getDeviceNumber(), fenceAlarmNum);

                return true;
            }else{
                fenceAlarmNum.put(fenceShapeVO.getFenceId(), num);
                deviceFenceAlarmNum.put(gpsInfo.getDeviceNumber(), fenceAlarmNum);
            }

        }else{
            Map<Long, Integer> fenceAlarmNum = new HashMap<>();
            fenceAlarmNum.put(fenceShapeVO.getFenceId(), 1);
            deviceFenceAlarmNum.put(gpsInfo.getDeviceNumber(), fenceAlarmNum);
        }
        return false;

    }



}
