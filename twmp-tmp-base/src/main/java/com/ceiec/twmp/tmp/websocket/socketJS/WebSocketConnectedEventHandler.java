//package com.ceiec.twmp.tmp.websocket;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.context.ApplicationListener;
//import org.springframework.stereotype.Component;
//import org.springframework.web.socket.messaging.SessionConnectedEvent;
//
//
///**
// * @author Ding
// * @version V1.0
// * @Description: the websocket connect event
// * @create 2019-03-12 11:35
// **/
//@Component
//public class WebSocketConnectedEventHandler implements ApplicationListener<SessionConnectedEvent> {
//    private Logger logger = LoggerFactory.getLogger(WebSocketConnectedEventHandler.class);
//
//    @Override
//    public void onApplicationEvent(SessionConnectedEvent event) {
//        String simpSessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
//        logger.info("new socket is connected! simpSessionId = " + simpSessionId);
//
//    }
//}
