package com.ceiec.twmp.tmp.vo.monitor.result;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;

/**
 * @version V1.0
 * @title: MonitorTaskQueryResultVO </br>
 * @createDate:  2019/3/15 11:49 </br>
 * @author：shihsh </br>
 * @description:  监控任务列表查询结果 </br>
 **/


public class MonitorTaskQueryResultVO extends PageParentVO {

    private static final long serialVersionUID = 1304158312944697439L;
    /**
     * 监控任务Id
     */
    private Long taskId;

    /**
     * 监控任务编号
     */
    private String taskCode;

    /**
     * 任务状态Id
     */
    private Byte taskStatus;

    /**
     * 任务进度值
     */
    private Integer taskProgress;

    /**
     * 监控开始时间
     */
    private Date startTime;

    /**
     * 监控结束时间
     */
    private Date endTime;

    /**
     * 监控等级
     */
    private Byte taskLevel;

    /**
     * 被监控人
     */
    private String personName;

    /**
     * 设备编号
     */
    private String deviceNumber;

    private Integer countSubmit;

    private Integer countChangeDevice;


    private boolean submitButton = false;

    private boolean changeParameterButton = false;

    private boolean changeDeviceButton = false;

    private boolean stopTaskButton = false;

    private boolean completeTaskButton = false;

    private boolean detailButton = false;

    private boolean detailEditButton = false;

    private boolean detailDelButton = false;

    private boolean exportInformButton = false;

    private boolean exportReportButton = false;


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

    public Byte getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Byte taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(Integer taskProgress) {
        this.taskProgress = taskProgress;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Byte getTaskLevel() {
        return taskLevel;
    }

    public void setTaskLevel(Byte taskLevel) {
        this.taskLevel = taskLevel;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber;
    }

    public Integer getCountSubmit() {
        return countSubmit;
    }

    public void setCountSubmit(Integer countSubmit) {
        this.countSubmit = countSubmit;
    }

    public Integer getCountChangeDevice() {
        return countChangeDevice;
    }

    public void setCountChangeDevice(Integer countChangeDevice) {
        this.countChangeDevice = countChangeDevice;
    }

    public boolean isSubmitButton() {
        return submitButton;
    }

    public void setSubmitButton(boolean submitButton) {
        this.submitButton = submitButton;
    }

    public boolean isChangeParameterButton() {
        return changeParameterButton;
    }

    public void setChangeParameterButton(boolean changeParameterButton) {
        this.changeParameterButton = changeParameterButton;
    }

    public boolean isChangeDeviceButton() {
        return changeDeviceButton;
    }

    public void setChangeDeviceButton(boolean changeDeviceButton) {
        this.changeDeviceButton = changeDeviceButton;
    }

    public boolean isStopTaskButton() {
        return stopTaskButton;
    }

    public void setStopTaskButton(boolean stopTaskButton) {
        this.stopTaskButton = stopTaskButton;
    }

    public boolean isCompleteTaskButton() {
        return completeTaskButton;
    }

    public void setCompleteTaskButton(boolean completeTaskButton) {
        this.completeTaskButton = completeTaskButton;
    }

    public boolean isDetailButton() {
        return detailButton;
    }

    public void setDetailButton(boolean detailButton) {
        this.detailButton = detailButton;
    }

    public boolean isDetailEditButton() {
        return detailEditButton;
    }

    public void setDetailEditButton(boolean detailEditButton) {
        this.detailEditButton = detailEditButton;
    }

    public boolean isDetailDelButton() {
        return detailDelButton;
    }

    public void setDetailDelButton(boolean detailDelButton) {
        this.detailDelButton = detailDelButton;
    }

    public boolean isExportInformButton() {
        return exportInformButton;
    }

    public void setExportInformButton(boolean exportInformButton) {
        this.exportInformButton = exportInformButton;
    }

    public boolean isExportReportButton() {
        return exportReportButton;
    }

    public void setExportReportButton(boolean exportReportButton) {
        this.exportReportButton = exportReportButton;
    }
}
