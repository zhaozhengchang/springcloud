//package com.ceiec.twmp.tmp.websocket;
//
//import com.ceiec.twmp.tmp.websocket.message.WebsocketMessageVO;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.messaging.simp.SimpMessagingTemplate;
//import org.springframework.stereotype.Controller;
//
///**
// * @author Ding
// * @version V1.0
// * @Description: websocket controller
// * @create 2019-03-12 10:05
// **/
//@Controller
//public class WebsocketController {
//
//    private static final Logger logger= LoggerFactory.getLogger(WebsocketController.class);
//    @MessageMapping("/chat")
//    //SendTo 发送至 Broker 下的指定订阅路径
//    @SendTo("/toAll/bulletScreen")
//    public String say(WebsocketMessageVO clientMessage) {
//        //方法用于广播测试
//        if (clientMessage!=null){
//            if (clientMessage.getMessage()!=null){
//                clientMessage.setMessage(clientMessage.getMessage().trim());
//            }
//        }
//        logger.info(clientMessage.getUsername()+":"+clientMessage.getMessage());
//        return clientMessage.getMessage();
//    }
//
////    //注入SimpMessagingTemplate 用于点对点消息发送
//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;
//}
