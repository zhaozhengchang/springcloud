package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;

import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: system message cache
 * @create 2019-03-26 16:04
 **/
public class SystemMessageRedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description update system message
     ** @param: userId
     ** @param: messageType
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 16:17
     **
     ************************************************************************************************************************************/
    protected static void addSystemMessage(String userId, MessageType messageType){

        RedisUserOnlineVO userOnlineVO = UserInfoRedis.getUserOnlineByUserId(Long.parseLong(userId));//if the user don't have the message type authority
        if(userOnlineVO!=null){
            if(StringUtils.isNullOrEmpty(userOnlineVO.getMessageType())||userOnlineVO.getMessageType().indexOf(Byte.toString(messageType.value))<=-1){
                return;
            }
        }

        String messageTypeStr = (String)getByMap(SYSTEM_MESSAGE, userId, String.class);


        if(StringUtils.isNullOrEmpty(messageTypeStr)){
            messageTypeStr = Byte.toString(messageType.value);
        }else{
            if(messageTypeStr.indexOf(Byte.toString(messageType.value))<=-1){//if the user has the same message type
                messageTypeStr = messageTypeStr+","+messageType.value;
            }
        }

        saveByMap(SYSTEM_MESSAGE, userId, messageTypeStr);
    }



    /*************************************************************************************************************************************
     ** @Description get system message
     ** @param:
     ** @Return java.util.Map<java.lang.Long,com.ceiec.twmp.tmp.common.dict.MessageType>
     ** @Author Ding
     ** @Date 2019/3/26 16:17
     **
     ************************************************************************************************************************************/
    public static Map<String, String>  getSystemMessage(){
        return  getMap(SYSTEM_MESSAGE, String.class);
    }


    /*************************************************************************************************************************************
     ** @Description delete user id of system message who has been sent
     ** @param: userId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 16:35
     **
     ************************************************************************************************************************************/
    public static void deleteSystemMessageByUserId(String userId){
        deleteByMap(SYSTEM_MESSAGE, userId);
    }
}
