package com.ceiec.twmp.tmp.vo.monitor.add;

import java.util.List;

/**
 * @version V1.0
 * @title: MonitorTaskAddVO </br>
 * @createDate：2019/3/12 17:26 </br>
 * @author：shihsh </br>
 * @description: 新增或编辑监控任务 </br>
 **/


public class MonitorTaskAddVO {

    /**
     *  请求类型
     */
    private byte taskType;

    /**
     * 监控Id
     */
    private Long taskId;

    /**
     * 监控任务编号
     */
    private String taskCode;

    /**
     * 监控任务名称
     */
    private String taskName;

    /**
     * 人员Id
     */
    private Long personId;

    /**
     * 被监控人员姓名
     */
    private String personName;

    /**
     * 删除的监控小组
     */
    private String deleteMonitorTaskTeamId;

    /**
     * 监护成员
     */
    private List<MonitorGuardianVO> guardianList;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时
     */
    private String endTime;

    /**
     * 监控等级
     */
    private Byte taskLevel;

    /**
     *  监控文书URL
     */
    private String documentUrl;

    /**
     * 监控原因
     */
    private String taskReason;

    /**
     * 删除电子围栏id
     */
    private String  deleteFenceId;

    /**
     * 围栏信息
     */
    private List<FenceVO> fenceList;

    /**
     * 开始汇报时间
     */
    private String reportStartTime;

    /**
     * 结束汇报时间
     */
    private String reportEndTime;

    /**
     * 汇报间隔时间
     */
    private Integer reportTimeInterval;

    /**
     * 汇报地点
     */
    private String reportLocation;

    /**
     * 汇报时刻
     */
    private String reportTime;

    public byte getTaskType() {
        return taskType;
    }

    public void setTaskType(byte taskType) {
        this.taskType = taskType;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public String getDeleteMonitorTaskTeamId() {
        return deleteMonitorTaskTeamId;
    }

    public void setDeleteMonitorTaskTeamId(String deleteMonitorTaskTeamId) {
        this.deleteMonitorTaskTeamId = deleteMonitorTaskTeamId;
    }

    public List<MonitorGuardianVO> getGuardianList() {
        return guardianList;
    }

    public void setGuardianList(List<MonitorGuardianVO> guardianList) {
        this.guardianList = guardianList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getTaskReason() {
        return taskReason;
    }

    public void setTaskReason(String taskReason) {
        this.taskReason = taskReason;
    }

    public String getDeleteFenceId() {
        return deleteFenceId;
    }

    public void setDeleteFenceId(String deleteFenceId) {
        this.deleteFenceId = deleteFenceId;
    }

    public List<FenceVO> getFenceList() {
        return fenceList;
    }

    public void setFenceList(List<FenceVO> fenceList) {
        this.fenceList = fenceList;
    }

    public String getReportStartTime() {
        return reportStartTime;
    }

    public void setReportStartTime(String reportStartTime) {
        this.reportStartTime = reportStartTime;
    }

    public String getReportEndTime() {
        return reportEndTime;
    }

    public void setReportEndTime(String reportEndTime) {
        this.reportEndTime = reportEndTime;
    }

    public Integer getReportTimeInterval() {
        return reportTimeInterval;
    }

    public void setReportTimeInterval(Integer reportTimeInterval) {
        this.reportTimeInterval = reportTimeInterval;
    }

    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getReportTime() {
        return reportTime;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
