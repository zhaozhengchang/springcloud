package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sm_alarm_dispose_ef")
public class TwmpSmAlarmDisposeEf {
    @Id
    @Column(name = "alarm_dispose_id")
    private Long alarmDisposeId;

    /**
     * 告警信息id
     */
    @Column(name = "alarm_id")
    private Long alarmId;

    /**
     * dispose_type
     */
    @Column(name = "dispose_type")
    private Byte disposeType;

    /**
     * 处置时间
     */
    @Column(name = "dispose_time")
    private Date disposeTime;

    /**
     * 处置该条报警信息的用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 处置该条报警信息的账号
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 备注说明
     */
    private String comment;

    /**
     * @return alarm_dispose_id
     */
    public Long getAlarmDisposeId() {
        return alarmDisposeId;
    }

    /**
     * @param alarmDisposeId
     */
    public void setAlarmDisposeId(Long alarmDisposeId) {
        this.alarmDisposeId = alarmDisposeId;
    }

    /**
     * 获取告警信息id
     *
     * @return alarm_id - 告警信息id
     */
    public Long getAlarmId() {
        return alarmId;
    }

    /**
     * 设置告警信息id
     *
     * @param alarmId 告警信息id
     */
    public void setAlarmId(Long alarmId) {
        this.alarmId = alarmId;
    }

    /**
     * 获取dispose_type
     *
     * @return dispose_type - dispose_type
     */
    public Byte getDisposeType() {
        return disposeType;
    }

    /**
     * 设置dispose_type
     *
     * @param disposeType dispose_type
     */
    public void setDisposeType(Byte disposeType) {
        this.disposeType = disposeType;
    }

    /**
     * 获取处置时间
     *
     * @return dispose_time - 处置时间
     */
    public Date getDisposeTime() {
        return disposeTime;
    }

    /**
     * 设置处置时间
     *
     * @param disposeTime 处置时间
     */
    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    /**
     * 获取处置该条报警信息的用户id
     *
     * @return user_id - 处置该条报警信息的用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置处置该条报警信息的用户id
     *
     * @param userId 处置该条报警信息的用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取处置该条报警信息的账号
     *
     * @return user_name - 处置该条报警信息的账号
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置处置该条报警信息的账号
     *
     * @param userName 处置该条报警信息的账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取备注说明
     *
     * @return comment - 备注说明
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置备注说明
     *
     * @param comment 备注说明
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}