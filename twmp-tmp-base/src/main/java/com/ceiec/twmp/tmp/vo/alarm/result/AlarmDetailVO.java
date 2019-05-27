package com.ceiec.twmp.tmp.vo.alarm.result;

import java.util.Date;
import java.util.List;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 15:03
 **/
public class AlarmDetailVO {

    private Long alarmId;

    private String alarmNumber;

    private Byte alarmType;

    private Date alarmTime;

    private String alarmTimeStr;

    private String longitude;

    private String latitude;

    private String deviceNumber;

    private String personName;

    private String phone;

    private String remark;

    private List<AlarmDisposeDetailVO> disposeList;

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String alarmNumber) {
        this.alarmNumber = alarmNumber;
    }

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmTimeStr() {
        return alarmTimeStr;
    }

    public void setAlarmTimeStr(String alarmTimeStr) {
        this.alarmTimeStr = alarmTimeStr;
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

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<AlarmDisposeDetailVO> getDisposeList() {
        return disposeList;
    }

    public void setDisposeList(List<AlarmDisposeDetailVO> disposeList) {
        this.disposeList = disposeList;
    }
}
