package com.ceiec.twmp.tmp.vo.alarm.result;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: pending allocation alarm info in redis
 * @create 2019-03-25 15:23
 **/
public class RedisPAAVO {

    private Long alarmId;

    private Long taskId;

    private Byte taskLevel;

    private Long departmentId;

    private Date alarmTime;

    private Byte alarmType;

    private String rejectUserId;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    public String getRejectUserId() {
        return rejectUserId;
    }

    public void setRejectUserId(String rejectUserId) {
        this.rejectUserId = rejectUserId;
    }
}
