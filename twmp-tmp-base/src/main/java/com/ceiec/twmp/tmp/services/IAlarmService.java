package com.ceiec.twmp.tmp.services;

import com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf;
import com.ceiec.twmp.tmp.utils.page.PagedItemsVO;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmAddVO;
import com.ceiec.twmp.tmp.vo.alarm.AlarmNumberVO;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmDisposeVO;
import com.ceiec.twmp.tmp.vo.alarm.query.AlarmQueryVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDetailVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmListVO;

import java.util.List;
import java.util.Map;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-18 10:24
 **/
public interface IAlarmService {

    void saveAlarm(TwmpSmAlarmEf twmpSmAlarmEf);

    /*************************************************************************************************************************************
     ** @Description  update alarm set hand_user and hand_user_id
     ** @param: alarmId
     ** @param: userId
     ** @param: userName
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 9:46
     **
     ************************************************************************************************************************************/
    void updateAlarmHandleUser(Long alarmId, Long userId, String handUserName);


    /*************************************************************************************************************************************
     ** @Description  update alarm set hand_user and hand_user_id batch
     ** @param: userAlarmMap
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 10:16
     **
     ************************************************************************************************************************************/
    void updateAlarmHandleUserBatch(Map<Long, List<Long>> userAlarmMap, Map<Long, String> userNameMap);

    /*************************************************************************************************************************************
     ** @Description get undistributed and to dispose alarm
     ** @param:
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf>
     ** @Author Ding
     ** @Date 2019/3/26 11:38
     **
     ************************************************************************************************************************************/
    List<TwmpSmAlarmEf> getUnDisposedAlarm();

    /*************************************************************************************************************************************
     ** @Description get to dispose alarm list by user id
     ** @param: userId
     ** @Return java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf>
     ** @Author Ding
     ** @Date 2019/3/26 14:45
     **
     ************************************************************************************************************************************/
    List<TwmpSmAlarmEf> getToDisposeAlarmByHandleUserId(Long handUserId);

    /*************************************************************************************************************************************
     ** @Description update alarm status from one status to another by hand user id
     ** @param: fromAlarmStatus
     ** @param: toAlarmStatus
     ** @param: userId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/3/26 14:55
     **
     ************************************************************************************************************************************/
    void updateAlarmStatusByHandUserId(Byte fromAlarmStatus, Byte toAlarmStatus, Long handUserId);

    AlarmNumberVO queryAlarmNumber(String token);

    PagedItemsVO queryToAllocateAlarm(AlarmQueryVO alarmQueryVO);

    List queryToDisposeAlarm(AlarmQueryVO alarmQueryVO);

    PagedItemsVO queryDisposedAlarm(AlarmQueryVO alarmQueryVO);
    /*************************************************************************************************************************************
     ** @Description claim alarm , then alarm from undistributed  -> toDispose
     ** @param: token
     ** @param: alarmId
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/22 14:54
     **
     ************************************************************************************************************************************/
    void claimAlarm(String token, Long alarmId);

    /*************************************************************************************************************************************
     ** @Description get alarm detail
     ** @param: alarmId
     ** @Return com.ceiec.twmp.tmp.vo.alarm.result.AlarmDetailVO
     ** @Author Ding
     ** @Date 2019/4/22 15:14
     **
     ************************************************************************************************************************************/
    AlarmDetailVO getAlarmDetail(Long alarmId);

    /*************************************************************************************************************************************
     ** @Description create alarm
     ** @param: token
     ** @param: alarmAddVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/22 15:49
     **
     ************************************************************************************************************************************/
    void createAlarm(String token, AlarmAddVO alarmAddVO);

    /*************************************************************************************************************************************
     ** @Description query disposed alarm by taskId
     ** @param: taskId
     ** @Return java.util.List<com.ceiec.twmp.tmp.vo.alarm.result.AlarmListVO>
     ** @Author Ding
     ** @Date 2019/4/22 17:11
     **
     ************************************************************************************************************************************/
    List<AlarmListVO> queryDisposedAlarmByTaskId(Long taskId);

    /*************************************************************************************************************************************
     ** @Description dispose alarm , if dispose type is backoff, the alarm is being back
     ** @param: token
     ** @param: alarmDisposeVO
     ** @Return void
     ** @Author Ding
     ** @Date 2019/4/23 10:01
     **
     ************************************************************************************************************************************/
    void disposeAlarm(String token, AlarmDisposeVO alarmDisposeVO);



}
