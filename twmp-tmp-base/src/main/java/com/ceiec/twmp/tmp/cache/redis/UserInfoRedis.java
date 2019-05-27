package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.common.dict.MessageSubType;
import com.ceiec.twmp.tmp.common.dict.MessageType;
import com.ceiec.twmp.tmp.utils.tools.ServerIdUtils;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO;

import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: about get or update user cache in redis
 * @create 2019-02-28 16:23
 **/
public class UserInfoRedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description  save user info
     ** @param: userCacheVo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/2/28 16:57
     **
     ************************************************************************************************************************************/
    public static void saveUser(RedisUserInfoVO redisUserInfoVo){
        saveEx(USER_INFO, redisUserInfoVo.getToken(), redisUserInfoVo.getSessionTime(), redisUserInfoVo);
    }

    /*************************************************************************************************************************************
     ** @Description get user info from redis
     ** @param: token
     ** @Return com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO
     ** @Author Ding
     ** @Date 2019/3/12 9:52
     **
     ************************************************************************************************************************************/
    public static RedisUserInfoVO getUser(String token){
        return (RedisUserInfoVO) get(USER_INFO, token, RedisUserInfoVO.class);
    }

    /*************************************************************************************************************************************
     ** @Description delete user
     ** @param: token
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 9:52
     **
     ************************************************************************************************************************************/
    public static void delUser(String token){
        RedisUserInfoVO redisUserInfoVO = getUser(token);
        delete(USER_INFO, token);
        delUserOnline(redisUserInfoVO);
    }

    /*************************************************************************************************************************************
     ** @Description get user online list
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO>
     ** @Author Ding
     ** @Date 2019/3/12 13:54
     **
     ************************************************************************************************************************************/
    public static Map<String, RedisUserOnlineVO>  getUserOnline(){
        return getMap(USER_ONLINE, RedisUserOnlineVO.class);
    }


    /*************************************************************************************************************************************
     ** @Description get RedisUserOnlineVO by userId
     ** @param: userId
     ** @Return com.ceiec.twmp.tmp.vo.user.result.RedisUserOnlineVO
     ** @Author Ding
     ** @Date 2019/3/27 16:56
     **
     ************************************************************************************************************************************/
    public static RedisUserOnlineVO getUserOnlineByUserId(Long userId){
        Map<String, RedisUserOnlineVO> userOnlineMap = getUserOnline();
        if(userOnlineMap!=null && userOnlineMap.size()>0){
            for(String token: userOnlineMap.keySet()){
                if(userOnlineMap.get(token).getUserId().equals(userId)){
                    return userOnlineMap.get(token);
                }
            }
        }
        return null;
    }

    /*************************************************************************************************************************************
     ** @Description save user online list
     ** @param: redisUserInfoVo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 13:55
     **
     ************************************************************************************************************************************/
    public static void saveUserOnline(RedisUserInfoVO redisUserInfoVo){
        RedisUserOnlineVO redisUserOnlineVO = new RedisUserOnlineVO();
        redisUserOnlineVO.setToken(redisUserInfoVo.getToken());
        redisUserOnlineVO.setUserId(redisUserInfoVo.getUserId());
        redisUserOnlineVO.setUserName(redisUserInfoVo.getUserName());
        redisUserOnlineVO.setServerId(ServerIdUtils.getServiceId());
        redisUserOnlineVO.setLoginId(redisUserInfoVo.getLoginId());
        redisUserOnlineVO.setOwnDepartmentId(redisUserInfoVo.getOwnDepartmentId());

        /** convert message sub type to message type **/
        StringBuilder messageType = new StringBuilder();

        List<MessageSubType> messageTypeList = redisUserInfoVo.getMessageSubTypes();
        if(messageTypeList!=null && messageTypeList.size()>0){
            for(MessageSubType messageSubType : messageTypeList){
                messageType = messageType.append(",").append(MessageType.get(messageSubType.parentValue).value);
            }
        }
        if(messageType.length()>1){
            messageType.delete(0,1);
        }
        redisUserOnlineVO.setMessageType(messageType.toString());



        saveByMap(USER_ONLINE, redisUserOnlineVO.getToken(), redisUserOnlineVO);

    }

    /*************************************************************************************************************************************
     ** @Description  plus user alarm allocation number one
     ** @param: token
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/28 16:15
     **
     ************************************************************************************************************************************/
    public static void userAANPlusOne(String token){
        RedisUserOnlineVO redisUserOnlineVO = new RedisUserOnlineVO();
        redisUserOnlineVO = (RedisUserOnlineVO)getByMap(USER_ONLINE, token, redisUserOnlineVO);
        redisUserOnlineVO.setAlarmAutomaticAllocationNum(redisUserOnlineVO.getAlarmAutomaticAllocationNum()+1);

        saveByMap(USER_ONLINE, token, redisUserOnlineVO);

    }

    /*************************************************************************************************************************************
     ** @Description set user alarm allocation number zero
     ** @param: token
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/28 16:18
     **
     ************************************************************************************************************************************/
    public static void clearAANP(String token){
        RedisUserOnlineVO redisUserOnlineVO = new RedisUserOnlineVO();
        redisUserOnlineVO = (RedisUserOnlineVO)getByMap(USER_ONLINE, token, redisUserOnlineVO);
        redisUserOnlineVO.setAlarmAutomaticAllocationNum(0);

        saveByMap(USER_ONLINE, token, redisUserOnlineVO);

    }


    /*************************************************************************************************************************************
     ** @Description delete user online
     ** @param: redisUserInfoVo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 14:02
     **
     ************************************************************************************************************************************/
    private static void delUserOnline(RedisUserInfoVO redisUserInfoVo){
        deleteByMap(USER_ONLINE, redisUserInfoVo.getToken());
    }


    public static void emptyUserOnline(){
        del(USER_ONLINE);
    }
}
