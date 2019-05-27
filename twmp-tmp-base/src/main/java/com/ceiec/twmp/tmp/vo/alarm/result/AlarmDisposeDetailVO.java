package com.ceiec.twmp.tmp.vo.alarm.result;

import java.util.Date;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-22 15:36
 **/
public class AlarmDisposeDetailVO {

    private Long alarmDisposeId;

    private Long alarmId;

    private Byte alarmType;

    private String userName;

    private String comment;

    private Date disposeTime;

    private String disposeTimeStr;

    public Long getAlarmDisposeId() {
        return alarmDisposeId;
    }

    public void setAlarmDisposeId(Long alarmDisposeId) {
        this.alarmDisposeId = alarmDisposeId;
    }

    public Long getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    public String getDisposeTimeStr() {
        return disposeTimeStr;
    }

    public void setDisposeTimeStr(String disposeTimeStr) {
        this.disposeTimeStr = disposeTimeStr;
    }
}
