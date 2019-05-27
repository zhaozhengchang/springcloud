package com.ceiec.twmp.tmp.websocket.ori;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import com.ceiec.twmp.tmp.websocket.message.WebsocketMessageVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: websocket connection & message handler
 * @create 2019-03-12 14:30
 **/
@Component
public class MessageWebSocketHandler implements WebSocketHandler {

    private  Map<Long, WebSocketSession> webSocketSessionMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(MessageWebSocketHandler.class);
    /*************************************************************************************************************************************
     ** @Description after websocket connected, update session into webSocketSessionMap by token
     ** @param: session
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 15:41
     **
     ************************************************************************************************************************************/
    @Override
    public void afterConnectionEstablished(WebSocketSession session) {//1
        String uri = session.getUri().toString();
        String token = uri.substring(uri.indexOf("token=")+6) ;
        Long userId = UserInfoRedis.getUser(token).getUserId();
        webSocketSessionMap.put(userId, session);
    }


    /*************************************************************************************************************************************
     ** @Description send message
     ** @param: session
     * @param: message
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 15:42
     **
     ************************************************************************************************************************************/
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage message) {//2
        try {
            session.sendMessage(message);
        } catch (IOException e) {
            logger.error("send websocket message is failed",e);
        }
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
    }

    /*************************************************************************************************************************************
     ** @Description  after websocket disconnected, remove session from webSocketSessionMap
     ** @param: session
     * @param: closeStatus
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 15:40
     **
     ************************************************************************************************************************************/
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) {
        for(Long key : webSocketSessionMap.keySet()){
            if(webSocketSessionMap.get(key) == session){
                webSocketSessionMap.remove(key);
                break;
            }
        }
    }
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /*************************************************************************************************************************************
     ** @Description  send message
     ** @param: userId
     * @param: websocketMessageVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 15:43
     **
     ************************************************************************************************************************************/
    public void sendMessage(Long userId, WebsocketMessageVO websocketMessageVO){
        WebSocketSession webSocketSession = webSocketSessionMap.get(userId);
        if(webSocketSession == null){
            logger.error("can't find this websocket session, please check user status");
            return;
        }

        String message = JSONObject.toJSONString(websocketMessageVO);
        TextMessage textMessage = new TextMessage(message);
        handleMessage(webSocketSession, textMessage);
    }
}
