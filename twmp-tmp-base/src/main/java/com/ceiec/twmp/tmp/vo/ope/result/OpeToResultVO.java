package com.ceiec.twmp.tmp.vo.ope.result;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description: ope to do reuslt vo
 * @create 2019-04-11 10:41
 **/
public class OpeToResultVO {

    private Long taskId;

    private String taskCode;

    private Long personId;

    private String personName;

    private Date taskBeginTime;

    private Date taskEndTime;

    private String taskBeginTimeStr;

    private String taskEndTimeStr;

    private Long deviceId;

    private String deviceNumber;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Date getTaskBeginTime() {
        return taskBeginTime;
    }

    public void setTaskBeginTime(Date taskBeginTime) {
        this.taskBeginTime = taskBeginTime;
    }

    public Date getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(Date taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTaskBeginTimeStr() {
        return taskBeginTimeStr;
    }

    public void setTaskBeginTimeStr(String taskBeginTimeStr) {
        this.taskBeginTimeStr = taskBeginTimeStr;
    }

    public String getTaskEndTimeStr() {
        return taskEndTimeStr;
    }

    public void setTaskEndTimeStr(String taskEndTimeStr) {
        this.taskEndTimeStr = taskEndTimeStr;
    }
}
