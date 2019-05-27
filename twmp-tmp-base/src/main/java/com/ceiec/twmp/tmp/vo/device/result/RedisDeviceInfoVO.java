package com.ceiec.twmp.tmp.vo.device.result;

import com.ceiec.twmp.tmp.vo.fence.FenceShapeVO;

import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description: vo of device in redis
 * @create 2019-03-12 9:39
 **/
public class RedisDeviceInfoVO {

    private Long deviceId;

    private String deviceNumber;

    private Long personId;

    private Long taskId;

    private String taskCode;

    private Byte taskLevel;

    private Byte onlineFlag;

    private Byte deviceType = 1;//the default value is 1 means ef

    private Byte gpsValid;

    private String gpsTime;

    private String longitude;

    private String latitude;

    private String direction;

    private String speed;

    private List<FenceShapeVO> fenceShapeList;

    private Byte opeStatus;

    private Byte taskStatus;

    private Long departmentId;

    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
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

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Byte getOpeStatus() {
        return opeStatus;
    }

    public void setOpeStatus(Byte opeStatus) {
        this.opeStatus = opeStatus;
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

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getOnlineFlag() {
        return onlineFlag;
    }

    public void setOnlineFlag(Byte onlineFlag) {
        this.onlineFlag = onlineFlag;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public Byte getGpsValid() {
        return gpsValid;
    }

    public void setGpsValid(Byte gpsValid) {
        this.gpsValid = gpsValid;
    }

    public String getGpsTime() {
        return gpsTime;
    }

    public void setGpsTime(String gpsTime) {
        this.gpsTime = gpsTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public List<FenceShapeVO> getFenceShapeList() {
        return fenceShapeList;
    }

    public void setFenceShapeList(List<FenceShapeVO> fenceShapeList) {
        this.fenceShapeList = fenceShapeList;
    }
}
