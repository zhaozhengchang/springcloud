package com.ceiec.twmp.tmp.websocket.message;

import com.alibaba.fastjson.JSONObject;
import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.common.enums.WebSocketMessageType;
import com.ceiec.twmp.tmp.entity.TwmpSysMessage;
import com.ceiec.twmp.tmp.cache.redis.SystemMessageRedis;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.vo.gps.GpsInfo;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;
import com.ceiec.twmp.tmp.websocket.ori.MessageWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: send message method
 * @create 2019-03-15 16:24
 **/
@Component
public class MessageSender extends SystemMessageRedis {

    @Autowired
    private MessageWebSocketHandler messageWebSocketHandler;

    /*************************************************************************************************************************************
     ** @Description send gps message
     ** @param: userId
     ** @param: gpsInfoList
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/15 16:36
     **
     ************************************************************************************************************************************/
    public void sendGpsListMessageImpl(Long userId, List<GpsInfo> gpsInfoList){
        WebsocketMessageVO websocketMessageVO = new WebsocketMessageVO();
        websocketMessageVO.setMessage(WebSocketMessageType.gps.desc);
        websocketMessageVO.setMessageType(WebSocketMessageType.gps.value);
        websocketMessageVO.setData(JSONObject.toJSONString(gpsInfoList));
        messageWebSocketHandler.sendMessage(userId, websocketMessageVO);

    }


    /*************************************************************************************************************************************
     ** @Description  send system message to some one
     ** @param: userId
     ** @param: messageType
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/27 16:09
     **
     ************************************************************************************************************************************/
    public void sendUserSystemMessage(Long userId, MessageType messageType){
        SystemMessageRedis.addSystemMessage(userId.toString(), messageType);
    }


    /*************************************************************************************************************************************
     ** @Description  send system message to some one who own the departmentId
     ** @param: departmentId
     ** @param: twmpSysMessage
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/27 16:24
     **
     ************************************************************************************************************************************/
    public void sendDepartmentMessage(Long departmentId, TwmpSysMessage twmpSysMessage){
        MessageType messageType = MessageType.get(twmpSysMessage.getMessageType());

        Map<String, RedisUserOnlineVO> redisUserOnlineVOMap = UserInfoRedis.getUserOnline();
        if(redisUserOnlineVOMap!=null && redisUserOnlineVOMap.size()>0){
            for(String token: redisUserOnlineVOMap.keySet()){
                RedisUserOnlineVO userOnlineVO = redisUserOnlineVOMap.get(token);
                if(userOnlineVO.getOwnDepartmentId().indexOf(departmentId.toString())>-1){
                    SystemMessageRedis.addSystemMessage(userOnlineVO.getUserId().toString(), messageType);
                }
            }
        }
    }


    /*************************************************************************************************************************************
     ** @Description  send system message
     ** @param: userId
     ** @param: messageType
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/22 16:51
     **
     ************************************************************************************************************************************/
    public void sendSystemMessageImpl(Long userId, MessageType messageType){
        WebsocketMessageVO websocketMessageVO = new WebsocketMessageVO();

        if(messageType == MessageType.allocation){
            websocketMessageVO.setMessage(WebSocketMessageType.newAllocation.desc);
            websocketMessageVO.setMessageType(WebSocketMessageType.newAllocation.value);
        }
        if(messageType == MessageType.alarm){
            websocketMessageVO.setMessage(WebSocketMessageType.newAlarm.desc);
            websocketMessageVO.setMessageType(WebSocketMessageType.newAlarm.value);
        }
        if(messageType == MessageType.notice){
            websocketMessageVO.setMessage(WebSocketMessageType.newNotice.desc);
            websocketMessageVO.setMessageType(WebSocketMessageType.newNotice.value);
        }

        websocketMessageVO.setData(messageType.comment);
        messageWebSocketHandler.sendMessage(userId, websocketMessageVO);
    }

}
