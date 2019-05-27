package com.ceiec.twmp.tmp.vo.monitor.approval.add;

/**
 * @title: DealApprovalVO </br>
 * @createDate: 2019/4/1 16:23 </br>
 * @author: shihsh  </br>
 * @description: 审批监控任务VO </br>
 * @version: V1.0
 **/


public class DealApprovalVO {
    private Long approvalId;

    private Long taskId;

    private Byte approvalType;

    private Byte approvalStatus;

    private String approvalNote;

    /**
     * 犯人是否逃跑
     */
    private Byte escape;

    public Long getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(Long approvalId) {
        this.approvalId = approvalId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Byte getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(Byte approvalType) {
        this.approvalType = approvalType;
    }

    public Long getTaskId() {
        return taskId;
    }

    public Byte getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(Byte approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalNote() {
        return approvalNote;
    }

    public void setApprovalNote(String approvalNote) {
        this.approvalNote = approvalNote;
    }
    public Byte getEscape() {
        return escape;
    }

    public void setEscape(Byte escape) {
        this.escape = escape;
    }
}
