package com.ceiec.twmp.tmp.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "twmp_monitor_task_approval_ef")
public class TwmpMonitorTaskApprovalEf {
    @Id
    @Column(name = "approval_id")
    private Long approvalId;

    /**
     * 监控任务id
     */
    @Column(name = "task_id")
    private Long taskId;

    /**
     * 监控任务编号
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 监控任务变更
     */
    @Column(name = "task_change_code")
    private String taskChangeCode;

    /**
     * 监控任务参数变更id（只有在审批类型为4时有值）
     */
    @Column(name = "task_change_id")
    private Long taskChangeId;

    /**
     * 审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)
     */
    @Column(name = "approval_type")
    private Byte approvalType;

    /**
     * 变更原因（设备变更审批需填写）
     */
    @Column(name = "change_reason")
    private String changeReason;

    /**
     * 提交人id
     */
    @Column(name = "submit_id")
    private Long submitId;

    /**
     * 提交人
     */
    private String submitter;

    /**
     * 提交时间
     */
    @Column(name = "submit_time")
    private Date submitTime;

    /**
     * 审批说明
     */
    @Column(name = "approval_note")
    private String approvalNote;

    /**
     * 审批人id
     */
    @Column(name = "approval_user_id")
    private Long approvalUserId;

    /**
     * 审批人
     */
    @Column(name = "approval_user")
    private String approvalUser;

    /**
     * 审批时间
     */
    @Column(name = "approval_time")
    private Date approvalTime;

    @Column(name = "approval_status")
    private Byte approvalStatus;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    /**
     * @return approval_id
     */
    public Long getApprovalId() {
        return approvalId;
    }

    /**
     * @param approvalId
     */
    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }

    /**
     * 获取监控任务id
     *
     * @return task_id - 监控任务id
     */
    public Long getTaskId() {
        return taskId;
    }

    /**
     * 设置监控任务id
     *
     * @param taskId 监控任务id
     */
    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskChangeCode() {
        return taskChangeCode;
    }

    public void setTaskChangeCode(String taskChangeCode) {
        this.taskChangeCode = taskChangeCode;
    }

    /**
     * 获取监控任务参数变更id（只有在审批类型为4时有值）
     *
     * @return task_change_id - 监控任务参数变更id（只有在审批类型为4时有值）
     */
    public Long getTaskChangeId() {
        return taskChangeId;
    }

    /**
     * 设置监控任务参数变更id（只有在审批类型为4时有值）
     *
     * @param taskChangeId 监控任务参数变更id（只有在审批类型为4时有值）
     */
    public void setTaskChangeId(Long taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    /**
     * 获取审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)
     *
     * @return approval_type - 审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)
     */
    public Byte getApprovalType() {
        return approvalType;
    }

    /**
     * 设置审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)
     *
     * @param approvalType 审批类型(1, 创建监控任务审批 2，结束监控任务审批3，设备变更审批 4，监控任务参数变更审批5，终止任务的审批)
     */
    public void setApprovalType(Byte approvalType) {
        this.approvalType = approvalType;
    }

    /**
     * 获取变更原因（设备变更审批需填写）
     *
     * @return change_reason - 变更原因（设备变更审批需填写）
     */
    public String getChangeReason() {
        return changeReason;
    }

    /**
     * 设置变更原因（设备变更审批需填写）
     *
     * @param changeReason 变更原因（设备变更审批需填写）
     */
    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    /**
     * 获取提交人id
     *
     * @return submit_id - 提交人id
     */
    public Long getSubmitId() {
        return submitId;
    }

    /**
     * 设置提交人id
     *
     * @param submitId 提交人id
     */
    public void setSubmitId(Long submitId) {
        this.submitId = submitId;
    }


    /**
     * 获取提交时间
     *
     * @return submit_time - 提交时间
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置提交时间
     *
     * @param submitTime 提交时间
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取审批说明
     * @return
     */
    public String getApprovalNote() {
        return approvalNote;
    }

    /**
     * 设置审批说明
     * @param approvalNote
     */
    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }

    /**
     * 获取审批人id
     *
     * @return approval_user_id - 审批人id
     */
    public Long getApprovalUserId() {
        return approvalUserId;
    }

    /**
     * 设置审批人id
     *
     * @param approvalUserId 审批人id
     */
    public void setApprovalUserId(Long approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    /**
     * 获取审批人
     *
     * @return approval_user - 审批人
     */
    public String getApprovalUser() {
        return approvalUser;
    }

    /**
     * 设置审批人
     *
     * @param approvalUser 审批人
     */
    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    /**
     * 获取审批时间
     *
     * @return approval_time - 审批时间
     */
    public Date getApprovalTime() {
        return approvalTime;
    }

    /**
     * 设置审批时间
     *
     * @param approvalTime 审批时间
     */
    public void setApprovalTime(Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    /**
     * @return approval_status
     */
    public Byte getApprovalStatus() {
        return approvalStatus;
    }

    /**
     * @param approvalStatus
     */
    public void setApprovalStatus(Byte approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}