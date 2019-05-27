package com.ceiec.twmp.tmp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "twmp_monitor_task_change_ef")
public class TwmpMonitorTaskChangeEf extends BaseEntity {
    @Id
    @Column(name = "task_change_id")
    private Long taskChangeId;

    /**
     * 绑定的设备id
     */
    @Column(name = "device_id")
    private Long deviceId;

    /**
     * 设备编号
     */
    @Column(name = "device_number")
    private String deviceNumber;

    @Column(name = "person_id")
    private Long personId;

    @Column(name = "person_name")
    private String personName;

    /**
     * 监控任务编号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 监控任务名称
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * 监控任务等级（1、高；2、中；3、低）
     */
    @Column(name = "task_level")
    private Byte taskLevel;

    /**
     * 任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）
     */
    @Column(name = "task_status")
    private Byte taskStatus;

    /**
     * 监控文书路径，多个下载路径已“，”串起来
     */
    @Column(name = "document_url")
    private String documentUrl;

    /**
     * 任务开始时间
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 任务结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 监控原因
     */
    @Column(name = "task_reason")
    private String taskReason;

    /**
     * 定期汇报地点
     */
    @Column(name = "report_location")
    private String reportLocation;

    /**
     * 定期汇报开始时间
     */
    @Column(name = "report_start_time")
    private Date reportStartTime;

    /**
     * 定期汇报结束时间
     */
    @Column(name = "report_end_time")
    private Date reportEndTime;

    /**
     * 定期汇报时间
     */
    @Column(name = "report_time")
    private Date reportTime;

    /**
     * 定期汇报频率（每隔几天，单位天）
     */
    @Column(name = "report_time_interval")
    private Date reportTimeInterval;

    /**
     * 组织机构id
     */
    @Column(name = "department_id")
    private Long departmentId;

    /**
     * 组织机构名称
     */
    @Column(name = "department_name")
    private String departmentName;

    /**
     * @return task_change_id
     */
    public Long getTaskChangeId() {
        return taskChangeId;
    }

    /**
     * @param taskChangeId
     */
    public void setTaskChangeId(Long taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    /**
     * 获取绑定的设备id
     *
     * @return device_id - 绑定的设备id
     */
    public Long getDeviceId() {
        return deviceId;
    }

    /**
     * 设置绑定的设备id
     *
     * @param deviceId 绑定的设备id
     */
    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * 获取设备编号
     *
     * @return device_number - 设备编号
     */
    public String getDeviceNumber() {
        return deviceNumber;
    }

    /**
     * 设置设备编号
     *
     * @param deviceNumber 设备编号
     */
    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    /**
     * @return person_id
     */
    public Long getPersonId() {
        return personId;
    }

    /**
     * @param personId
     */
    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    /**
     * 获取监控任务编号
     *
     * @return task_code - 监控任务编号
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * 设置监控任务编号
     *
     * @param taskCode 监控任务编号
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    /**
     * 获取监控任务名称
     *
     * @return task_name - 监控任务名称
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * 设置监控任务名称
     *
     * @param taskName 监控任务名称
     */
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    /**
     * 获取监控任务等级（1、高；2、中；3、低）
     *
     * @return task_level - 监控任务等级（1、高；2、中；3、低）
     */
    public Byte getTaskLevel() {
        return taskLevel;
    }

    /**
     * 设置监控任务等级（1、高；2、中；3、低）
     *
     * @param taskLevel 监控任务等级（1、高；2、中；3、低）
     */
    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    /**
     * 获取任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）
     *
     * @return task_status - 任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）
     */
    public Byte getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）
     *
     * @param taskStatus 任务状态（1未开始、2 已开始、3 已完成、4 已结束5已终止）
     */
    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取监控文书路径，多个下载路径已“，”串起来
     *
     * @return document_url - 监控文书路径，多个下载路径已“，”串起来
     */
    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * 设置监控文书路径，多个下载路径已“，”串起来
     *
     * @param documentUrl 监控文书路径，多个下载路径已“，”串起来
     */
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    /**
     * 获取任务开始时间
     *
     * @return start_time - 任务开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置任务开始时间
     *
     * @param startTime 任务开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取任务结束时间
     *
     * @return end_time - 任务结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置任务结束时间
     *
     * @param endTime 任务结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取监控原因
     *
     * @return task_reason - 监控原因
     */
    public String getTaskReason() {
        return taskReason;
    }

    /**
     * 设置监控原因
     *
     * @param taskReason 监控原因
     */
    public void setTaskReason(String taskReason) {
        this.taskReason = taskReason;
    }

    /**
     * 获取定期汇报地点
     *
     * @return report_location - 定期汇报地点
     */
    public String getReportLocation() {
        return reportLocation;
    }

    /**
     * 设置定期汇报地点
     *
     * @param reportLocation 定期汇报地点
     */
    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    /**
     * 获取定期汇报开始时间
     *
     * @return report_start_time - 定期汇报开始时间
     */
    public Date getReportStartTime() {
        return reportStartTime;
    }

    /**
     * 设置定期汇报开始时间
     *
     * @param reportStartTime 定期汇报开始时间
     */
    public void setReportStartTime(Date reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    /**
     * 获取定期汇报结束时间
     *
     * @return report_end_time - 定期汇报结束时间
     */
    public Date getReportEndTime() {
        return reportEndTime;
    }

    /**
     * 设置定期汇报结束时间
     *
     * @param reportEndTime 定期汇报结束时间
     */
    public void setReportEndTime(Date reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    /**
     * 获取定期汇报时间
     *
     * @return report_time - 定期汇报时间
     */
    public Date getReportTime() {
        return reportTime;
    }

    /**
     * 设置定期汇报时间
     *
     * @param reportTime 定期汇报时间
     */
    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    /**
     * 获取定期汇报频率（每隔几天，单位天）
     *
     * @return report_time_interval - 定期汇报频率（每隔几天，单位天）
     */
    public Date getReportTimeInterval() {
        return reportTimeInterval;
    }

    /**
     * 设置定期汇报频率（每隔几天，单位天）
     *
     * @param reportTimeInterval 定期汇报频率（每隔几天，单位天）
     */
    public void setReportTimeInterval(Date reportTimeInterval) {
        this.reportTimeInterval = reportTimeInterval;
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
     * 获取组织机构名称
     *
     * @return department_name - 组织机构名称
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * 设置组织机构名称
     *
     * @param departmentName 组织机构名称
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}