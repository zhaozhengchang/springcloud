package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_sm_device_alarm_person_report")
public class TwmpSmDeviceAlarmPersonReport {
    @Id
    @Column(name = "report_id")
    private Long reportId;

    /**
     * 被监控人员id
     */
    @Column(name = "person_id")
    private Long personId;

    /**
     * 告警信息数量
     */
    private Long num;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 告警时间（按天分隔）
     */
    @Column(name = "alarm_time")
    private Date alarmTime;

    /**
     * 告警类型
     */
    @Column(name = "alarm_type")
    private Byte alarmType;

    public Byte getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Byte alarmType) {
        this.alarmType = alarmType;
    }

    /**
     * @return report_id
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * @param reportId
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    /**
     * 获取被监控人员id
     *
     * @return person_id - 被监控人员id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * 设置被监控人员id
     *
     * @param personId 被监控人员id
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取告警信息数量
     *
     * @return num - 告警信息数量
     */
    public Long getNum() {
        return num;
    }

    /**
     * 设置告警信息数量
     *
     * @param num 告警信息数量
     */
    public void setNum(Long num) {
        this.num = num;
    }

    /**
     * 获取组织机构id
     *
     * @return department_id - 组织机构id
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * 设置组织机构id
     *
     * @param departmentId 组织机构id
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * 获取告警时间（按天分隔）
     *
     * @return alarm_time - 告警时间（按天分隔）
     */
    public Date getAlarmTime() {
        return alarmTime;
    }

    /**
     * 设置告警时间（按天分隔）
     *
     * @param alarmTime 告警时间（按天分隔）
     */
    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }
}