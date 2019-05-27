package com.ceiec.twmp.tmp.controller;

import com.ceiec.twmp.tmp.ResponseType;
import com.ceiec.twmp.tmp.cache.redis.UserInfoRedis;
import com.ceiec.twmp.tmp.common.dict.AlarmStatus;
import com.ceiec.twmp.tmp.services.IAlarmService;
import com.ceiec.twmp.tmp.utils.DepartmentStrToList;
import com.ceiec.twmp.tmp.utils.response.ResponseContent;
import com.ceiec.twmp.tmp.utils.tools.StringUtils;
import com.ceiec.twmp.tmp.utils.tools.date.DateFormatUtils;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmAddVO;
import com.ceiec.twmp.tmp.vo.alarm.add.AlarmDisposeVO;
import com.ceiec.twmp.tmp.vo.alarm.query.AlarmQueryVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDetailVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmListVO;
import com.ceiec.twmp.tmp.vo.user.result.RedisUserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description:controller for monitor alarm
 * @create 2019-04-22 10:15
 **/
@RestController
@RequestMapping("/alarm")
public class AlarmController {


    @Autowired
    private IAlarmService alarmService;


    /*************************************************************************************************************************************
     ** @Description query AlarmNumberVO number , the condition is default
     ** @param: token
     ** @param: addressName
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/18 10:13
     **
     ************************************************************************************************************************************/
    @PostMapping("/alarmNumber")
    public ResponseContent alarmNumber(@RequestHeader String token) {
        return new  ResponseContent(ResponseType.SUCCESS , alarmService.queryAlarmNumber(token));
    }


    /*************************************************************************************************************************************
     ** @Description  query alarm list  include  to dispose, undistributed, disposed
     ** @param: token
     ** @param: alarmNumberVO
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 10:30
     **
     ************************************************************************************************************************************/
    @PostMapping("/alarmList")
    public ResponseContent alarmList(@RequestHeader String token, @RequestBody AlarmQueryVO alarmQueryVO) {
        RedisUserInfoVO user = UserInfoRedis.getUser(token);

        alarmQueryVO.setOwnDepartmentId(DepartmentStrToList.getDepartmentIdList(token));

        if(!StringUtils.isNullOrEmpty(alarmQueryVO.getBeginTimeStr())){
            alarmQueryVO.setBeginTime(DateFormatUtils.stringToDateTime(alarmQueryVO.getBeginTimeStr()));
        }
        if(!StringUtils.isNullOrEmpty(alarmQueryVO.getEndTimeStr())){
            alarmQueryVO.setEndTime(DateFormatUtils.stringToDateTime(alarmQueryVO.getEndTimeStr()));
        }

        if(alarmQueryVO.getAlarmStatus() == AlarmStatus.toDispose.value){
            alarmQueryVO.setUserId(user.getUserId());
            return new  ResponseContent(ResponseType.SUCCESS , alarmService.queryToDisposeAlarm(alarmQueryVO));
        }else if(alarmQueryVO.getAlarmStatus() == AlarmStatus.undistributed.value){
            return new  ResponseContent(ResponseType.SUCCESS , alarmService.queryToAllocateAlarm(alarmQueryVO));
        }else if(alarmQueryVO.getAlarmStatus() == AlarmStatus.disposed.value){
            return new  ResponseContent(ResponseType.SUCCESS , alarmService.queryDisposedAlarm(alarmQueryVO));
        }else{
            return new  ResponseContent(ResponseType.ILLEGAL_PARAMETER , null);
        }

    }


    /*************************************************************************************************************************************
     ** @Description claim alarm
     ** @param: token
     ** @param: alarmId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 14:59
     **
     ************************************************************************************************************************************/
    @PostMapping("/claimAlarm/{alarmId}")
    public ResponseContent claimAlarm(@RequestHeader String token, @PathVariable Long alarmId) {
        alarmService.claimAlarm(token, alarmId);

        return new  ResponseContent(ResponseType.SUCCESS);
    }


    /*************************************************************************************************************************************
     ** @Description get alarm detail
     ** @param: token
     ** @param: alarmId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 15:02
     **
     ************************************************************************************************************************************/
    @PostMapping("/alarmDetail/{alarmId}")
    public ResponseContent alarmDetail(@RequestHeader String token, @PathVariable Long alarmId) {
        AlarmDetailVO alarmDetailVO = alarmService.getAlarmDetail(alarmId);

        return new  ResponseContent(ResponseType.SUCCESS, alarmDetailVO);
    }


    /*************************************************************************************************************************************
     ** @Description create alarm by user
     ** @param: token
     ** @param: alarmId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 15:46
     **
     ************************************************************************************************************************************/
    @PostMapping("/createAlarm")
    public ResponseContent alarmDetail(@RequestHeader String token, @RequestBody AlarmAddVO alarmAddVO) {
        alarmService.createAlarm(token, alarmAddVO);

        return new  ResponseContent(ResponseType.SUCCESS);
    }

    /*************************************************************************************************************************************
     ** @Description get disposed alarm by task id
     ** @param: token
     ** @param: taskId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/22 17:20
     **
     ************************************************************************************************************************************/
    @PostMapping("/taskDisposedAlarm/{taskId}")
    public ResponseContent taskDisposedAlarm(@RequestHeader String token, @PathVariable Long taskId) {
        List<AlarmListVO> list =  alarmService.queryDisposedAlarmByTaskId(taskId);

        return new  ResponseContent(ResponseType.SUCCESS, list);
    }


    /*************************************************************************************************************************************
     ** @Description dispose alarm
     ** @param: token
     ** @param: taskId
     ** @Return com.ceiec.twmp.tmp.utils.response.ResponseContent
     ** @Author Ding
     ** @Date 2019/4/23 9:59
     **
     ************************************************************************************************************************************/
    @PostMapping("/alarmDispose")
    public ResponseContent alarmDispose(@RequestHeader String token, @RequestBody AlarmDisposeVO alarmDisposeVO) {
        alarmService.disposeAlarm(token, alarmDisposeVO);

        return new  ResponseContent(ResponseType.SUCCESS);
    }




}
