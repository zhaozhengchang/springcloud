package com.ceiec.twmp.tmp.vo.monitor.result;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 16:12
 **/
public class TaskResultVO {

    private Long taskId;

    private String taskCode;

    private Long personId;

    private String personName;

    private Long deviceId;

    private String deviceNumber;

    private Long departmentId;

    private String departmentName;

    private Byte taskLevel;

    private Byte onlineStatus;

    private String devicePower;

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public Byte getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(Byte onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public String getDevicePower() {
        return devicePower;
    }

    public void setDevicePower(String devicePower) {
        this.devicePower = devicePower;
    }
}
