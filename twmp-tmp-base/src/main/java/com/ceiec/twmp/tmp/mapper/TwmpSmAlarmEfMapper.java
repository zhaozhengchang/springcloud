package com.ceiec.twmp.tmp.mapper;

import com.ceiec.twmp.tmp.entity.TwmpSmAlarmEf;
import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmPersonReport;
import com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport;
import com.ceiec.twmp.tmp.vo.alarm.query.AlarmQueryVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmDetailVO;
import com.ceiec.twmp.tmp.vo.alarm.result.AlarmListVO;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component(value = "twmpSmAlarmEfMapper")
public interface TwmpSmAlarmEfMapper extends Mapper<TwmpSmAlarmEf> {


    /**
     * @description:按天统计各种设备告警数量
     * @Param: time
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/26 11:19
     */
    List<TwmpSmAlarmEf> queryAlarmByDay(String time);

    /**
     * @description:分组统计数据
     * @Param: time
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmReport>
     * @author: zhaozhengchang
     * @date: 2019/3/26 14:57
     */
    List<TwmpSmDeviceAlarmReport> groupByAlarmTypeAndDepartment(String time);

    void updateAlarmHandleUserBatch(List<TwmpSmAlarmEf> list);

    List<TwmpSmAlarmEf> getUnDisposedAlarm(Byte alarmStatus);

    void updateAlarmStatusByHandUserId(Byte fromAlarmStatus, Byte toAlarmStatus, Long handUserId);

    Long countToDisposeAlarmNum(Long userId);

    Long countToAllocateNum(List<Long> ownDepartmentId);

    Long countDisposedAlarmNum(List<Long> ownDepartmentId);

    List<AlarmListVO> queryAlarm(AlarmQueryVO alarmQueryVO);

    Long countAlarm(AlarmQueryVO alarmQueryVO);

    AlarmDetailVO getAlarmDetail(Long alarmId);

    List<AlarmListVO> queryDisposedAlarmByTaskId(Long taskId);

    /**
     * @description:分组统计数据
     * @Param: time
     * @return: java.util.List<com.ceiec.twmp.tmp.entity.TwmpSmDeviceAlarmPersonReport>
     * @author: zhaozhengchang
     * @date: 2019/3/26 14:57
     */
    List<TwmpSmDeviceAlarmPersonReport> groupByPersonAndDepartment(String time);
}