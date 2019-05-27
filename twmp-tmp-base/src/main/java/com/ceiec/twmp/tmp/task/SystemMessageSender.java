package com.ceiec.twmp.tmp.task;

import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.websocket.message.MessageSender;
import com.ceiec.twmp.tmp.cache.redis.SystemMessageRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: send  system message , because of some user online is not in this server, so we need share the system message and let all of our server notice user
 * @create 2019-03-26 16:18
 **/
@Component
@Order(value=102)
public class SystemMessageSender implements CommandLineRunner {
    @Autowired
    private MessageSender messageSender;

    private static Logger logger = LoggerFactory.getLogger(SystemMessageSender.class);

    @Override
    public void run(String... args)   {
        new SendSystemMessageThread().start();
    }


    class SendSystemMessageThread extends Thread{
        @Override
        public void run() {

            while(true){

                try{
                    Map<String, String> systemMessageMap = SystemMessageRedis.getSystemMessage();
                    if(systemMessageMap!=null && systemMessageMap.size()>0){
                        for(String userId: systemMessageMap.keySet()){
                            String[] messageTypeArray = systemMessageMap.get(userId).split(",");
                            for(int i=0; i<messageTypeArray.length; i++){
                                messageSender.sendSystemMessageImpl(Long.parseLong(userId), MessageType.get(Byte.parseByte(messageTypeArray[i])));
                            }
                            SystemMessageRedis.deleteSystemMessageByUserId(userId);
                        }
                    }
                }catch (Exception e){
                    logger.error("send system message thread run failed ",e);
                }

                try {
                    sleep(1000); //every 1000 millis send message once
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }

    }
}
