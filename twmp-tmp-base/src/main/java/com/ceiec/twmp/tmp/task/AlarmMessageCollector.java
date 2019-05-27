package com.ceiec.twmp.tmp.task;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.common.enums.EKafkaGroup;
import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.kafka.ConsumerFactory;
import com.ceiec.twmp.tmp.kafka.KafkaFactory;
import com.ceiec.twmp.tmp.websocket.message.MessageSender;
import com.ceiec.twmp.tmp.utils.tools.ServerIdUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Ding
 * @version V1.0
 * @Description: send message of alarm
 * @create 2019-03-22 15:27
 **/
@Component
public class AlarmMessageCollector {

    @Autowired
    private MessageSender messageSender;

    private static Logger logger = LoggerFactory.getLogger(GpsMessageCollectorSender.class);

    private static KafkaConsumer consumer;

    private static ConcurrentHashMap<Long, Map<MessageType, Boolean>> userNewMessageMap = new ConcurrentHashMap<>();

    private static long period = 5*1000; //send message per period

    private static ExecutorService executorService = new ThreadPoolExecutor(10,10,30, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(10, true),
            new ThreadPoolExecutor.CallerRunsPolicy());



    private void init(){
        consumer = KafkaFactory.getKafkaConsumer(EKafkaGroup.GROUP_ALARM_MESSAGE+ ServerIdUtils.getServiceId());
        ConsumerFactory.setConsumerTopic(consumer, EKafkaTopic.TOPIC_ALARM_MESSAGE);

        executorService.execute(new GetAlarmMessage());
//        executorService.execute(new SendAlarmMessage());

    }



    /*************************************************************************************************************************************
     ** @Description get system message from kafka
     ** @Return
     ** @Author Ding
     ** @Date 2019/3/22 17:12
     **
     ************************************************************************************************************************************/
    private class GetAlarmMessage extends Thread{
        @Override
        public void run(){
            while(true){
                try{
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    for (ConsumerRecord<String, String> record : records)
                    {
                        TwmpSysMessage message = (TwmpSysMessage) JSONObject.parse(record.value());
                        messageSender.sendDepartmentMessage(message.getDepartmentId(), message);

                    }
                }catch (Exception e){
                    logger.error("get system message info is failed", e);
                }
            }
        }
    }

//    /*************************************************************************************************************************************
//     ** @Description  classify message into group
//     ** @param: message
//     ** @Return void
//     ** @Author Ding
//     ** @Date 2019/3/22 17:06
//     **
//     ************************************************************************************************************************************/
//    private static void classifyAlarmMessage(TwmpSysMessage message) {
//
//        Map<String, RedisUserOnlineVO> userOnlineVOMap = UserInfoRedis.getUserOnline();
//        if(userOnlineVOMap!=null && userOnlineVOMap.size()>0){
//            for(String key: userOnlineVOMap.keySet()){
//                RedisUserOnlineVO redisUserOnlineVO = userOnlineVOMap.get(key);
//                if(redisUserOnlineVO.getServerId().equals(ServerIdUtils.getServiceId()) //confirm if it  belongs this server
//                        && redisUserOnlineVO.getOwnDepartmentId().indexOf(message.getDepartmentId().toString())>-1){ //confirm the user has the authority
//                    Map<MessageType, Boolean> map = userNewMessageMap.get(redisUserOnlineVO.getUserId());
//                    if(map == null){
//                        map = new HashMap<>();
//                    }
//                    map.put(MessageType.get(message.getMessageType()), Boolean.TRUE);
//                }
//            }
//        }
//    }
//
//
//
//    /*************************************************************************************************************************************
//     ** @Description send system message
//     ** @Return
//     ** @Author Ding
//     ** @Date 2019/3/22 17:09
//     **
//     ************************************************************************************************************************************/
//    private class SendAlarmMessage extends Thread{
//        @Override
//        public void run(){
//            while(true){
//                try{
//                    if(userNewMessageMap !=null && userNewMessageMap.size()>0){
//
//                        for(Long userId : userNewMessageMap.keySet()){
//                            if(userNewMessageMap.get(userId)!=null && userNewMessageMap.get(userId).size()>0){
//                                Map<MessageType, Boolean> map = userNewMessageMap.get(userId);
//                                for(MessageType messageType: map.keySet()){
//                                    if(map.get(messageType)){
//                                        messageSender.sendSystemMessage(userId, messageType);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    sleep(period);
//                }catch (Exception e){
//                    logger.error("send system message is failed",e);
//                }
//            }
//        }
//    }

}
