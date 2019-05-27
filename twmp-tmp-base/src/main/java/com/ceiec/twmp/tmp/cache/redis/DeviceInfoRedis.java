package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO;

/**
 * @author Ding
 * @version V1.0
 * @Description: about get or update device cache in redis
 * @create 2019-03-12 9:50
 **/
public class DeviceInfoRedis extends BaseRedis {
    /*************************************************************************************************************************************
     ** @Description  save device info in redis
     ** @param: redisDeviceInfoVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 9:57
     **
     ************************************************************************************************************************************/
    public static void saveDevice(RedisDeviceInfoVO redisDeviceInfoVO){
        save(DEVICE_INFO, redisDeviceInfoVO.getDeviceNumber(), redisDeviceInfoVO);
    }

    /*************************************************************************************************************************************
     ** @Description get device info from redis
     ** @param: deviceNumber
     ** @Return com.ceiec.twmp.tmp.vo.device.result.RedisDeviceInfoVO
     ** @Author Ding
     ** @Date 2019/3/12 9:58
     **
     ************************************************************************************************************************************/
    public static RedisDeviceInfoVO getDevice(String deviceNumber){
        return (RedisDeviceInfoVO)get(DEVICE_INFO, deviceNumber, RedisDeviceInfoVO.class);
    }

    /*************************************************************************************************************************************
     ** @Description delete device
     ** @param: deviceNumber
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/12 9:58
     **
     ************************************************************************************************************************************/
    public static void delDevice(String deviceNumber){
        delete(DEVICE_INFO, deviceNumber);
    }
}
