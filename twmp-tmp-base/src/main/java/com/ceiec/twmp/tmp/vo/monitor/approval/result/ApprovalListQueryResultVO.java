package com.ceiec.twmp.tmp.vo.monitor.approval.result;

import com.ceiec.twmp.tmp.utils.page.PageParentVO;

import java.util.Date;

/**
 * @title: ApprovalListQueryResultVO </br>
 * @createDate: 2019/3/27 10:55 </br>
 * @author: shihsh  </br>
 * @description: 审批列表查询结果 </br>
 * @version: V1.0
 **/


public class ApprovalListQueryResultVO extends PageParentVO {

    private static final long serialVersionUID = 8271540037572167640L;

    private Long approvalId;

    private Long taskId;

    private String taskCode;

    private Long taskChangeId;

    private String taskChangeCode;

    private Byte approvalType;

    private String changeReason;

    private String submitter;

    private Long submitId;

    private String submitTime;

    private Date submitTimeDate;

    private String approvalUser;

    private Long approvalUserId;

    private String approvalTime;

    private Date approvalTimeDate;

    private Byte approvalStatus;

    private Long departmentId;

    private String departmentName;

    public String getTaskChangeCode() {
        return taskChangeCode;
    }

    public void setTaskChangeCode(String taskChangeCode) {
        this.taskChangeCode = taskChangeCode;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

    public Date getSubmitTimeDate() {
        return submitTimeDate;
    }

    public void setSubmitTimeDate(Date submitTimeDate) {
        this.submitTimeDate = submitTimeDate;
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

    public Long getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskChangeId() {
        return taskChangeId;
    }

    public void setTaskChangeId(Long taskChangeId) {
        this.taskChangeId = taskChangeId;
    }

    public Byte getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Byte approvalType) {
        this.approvalType = approvalType;
    }

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Long getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Long submitId) {
        this.submitId = submitId;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }


    public String getApprovalUser() {
        return approvalUser;
    }

    public void setApprovalUser(String approvalUser) {
        this.approvalUser = approvalUser;
    }

    public Long getApprovalUserId() {
        return approvalUserId;
    }

    public void setApprovalUserId(Long approvalUserId) {
        this.approvalUserId = approvalUserId;
    }

    public String getApprovalTime() {
        return approvalTime;
    }

    public void setApprovalTime(String approvalTime) {
        this.approvalTime = approvalTime;
    }

    public Date getApprovalTimeDate() {
        return approvalTimeDate;
    }

    public void setApprovalTimeDate(Date approvalTimeDate) {
        this.approvalTimeDate = approvalTimeDate;
    }

    public Byte getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Byte approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
}
