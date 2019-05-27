package com.ceiec.twmp.tmp.cache.redis;

import com.ceiec.twmp.tmp.vo.alarm.result.RedisPAAVO;

import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description: pending allocation alarm info in redis
 * @create 2019-03-25 15:29
 **/
public class PAARedis extends BaseRedis {

    /*************************************************************************************************************************************
     ** @Description get pending alarm allocation
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.alarm.result.RedisPAAVO>
     ** @Author Ding
     ** @Date 2019/3/25 15:34
     **
     ************************************************************************************************************************************/
    public static Map<String, RedisPAAVO> getPAA(){
        return getMap(ALARM_PENDING_ALLOCATION, RedisPAAVO.class);
    }


    /*************************************************************************************************************************************
     ** @Description save pending alarm allocation
     ** @param: paaList
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/25 15:35
     **
     ************************************************************************************************************************************/
    public static void addPAA(RedisPAAVO paaVo){
        saveByMap(ALARM_PENDING_ALLOCATION, paaVo.getAlarmId().toString(), paaVo);

    }


    /*************************************************************************************************************************************
     ** @Description delete pending alarm allocation
     ** @param: paaVo
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 11:30
     **
     ************************************************************************************************************************************/
    public static void delPAA(Long alarmId){
        deleteByMap(ALARM_PENDING_ALLOCATION, alarmId.toString());
    }

    /*************************************************************************************************************************************
     ** @Description clear pending alarm allocation
     ** @param:
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 11:56
     **
     ************************************************************************************************************************************/
    public static void setPaaEmpty(){
        del(ALARM_PENDING_ALLOCATION);
    }

}
