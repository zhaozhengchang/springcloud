package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.vo.user.result.RedisUserSubscriptionVO;

import java.util.Set;

/**
 * @author Ding
 * @version V1.0
 * @Description: redis cache for user subscription, when user subscribe some devices, they will receive the gps of these devices
 * @create 2019-03-14 14:50
 **/
public class UserSubscriptionRedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description save user subscription
     ** @param: userId
     ** @param: deviceNumber
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/14 14:59
     **
     ************************************************************************************************************************************/
    public static void saveUserSubscription(Long userId, Set<String> deviceNumber){
        RedisUserSubscriptionVO redisUserSubscriptionVO = new RedisUserSubscriptionVO();
        redisUserSubscriptionVO.setUserId(userId);
        redisUserSubscriptionVO.setDeviceNumber(deviceNumber);
        save(USER_SUBSCRIPTION, userId.toString(), redisUserSubscriptionVO);
    }


    /*************************************************************************************************************************************
     ** @Description get user subscription
     ** @param: userId
     ** @Return com.ceiec.twmp.tmp.vo.user.result.RedisUserSubscriptionVO
     ** @Author Ding
     ** @Date 2019/3/15 10:15
     **
     ************************************************************************************************************************************/
    public static RedisUserSubscriptionVO getUserSubscription(Long userId){
        return (RedisUserSubscriptionVO) get(USER_SUBSCRIPTION, userId.toString(), RedisUserSubscriptionVO.class);
    }


    /*************************************************************************************************************************************
     ** @Description delete user subscription
     ** @param: userId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/15 10:30
     **
     ************************************************************************************************************************************/
    public static void deleteUserSubscription(Long userId){
        delete(USER_SUBSCRIPTION, userId.toString());
    }


    /*************************************************************************************************************************************
     ** @Description delete deviceId in someone subscription
     ** @param: userId
     ** @param: deviceNumber
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/15 10:33
     **
     ************************************************************************************************************************************/
    public static void deleteByDeviceId(Long userId, Long deviceNumber){
        RedisUserSubscriptionVO redisUserSubscriptionVO = getUserSubscription(userId);
        Set<String> deviceNumbers = redisUserSubscriptionVO.getDeviceNumber();
        if(deviceNumbers!=null && deviceNumbers.size()>0){
            deviceNumbers.remove(deviceNumber);
        }
        saveUserSubscription(userId, deviceNumbers);
    }

}
