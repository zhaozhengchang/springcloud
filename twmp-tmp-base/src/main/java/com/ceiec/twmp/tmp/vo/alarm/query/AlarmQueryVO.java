package com.ceiec.twmp.tmp.vo.alarm.query;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 10:20
 **/
public class AlarmQueryVO extends PageParentVO {

    private String taskCode;

    private String personName;

    private String deviceNumber;

    private String beginTimeStr;

    private Date beginTime;

    private String endTimeStr;

    private Date endTime;

    private Byte alarmStatus;

    private Byte alarmType;

    private Long userId;

    private List<Long> ownDepartmentId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getOwnDepartmentId() {
        return ownDepartmentId;
    }

    public void setOwnDepartmentId(List<Long> ownDepartmentId) {
        this.ownDepartmentId = ownDepartmentId;
    }

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Byte alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }
}
