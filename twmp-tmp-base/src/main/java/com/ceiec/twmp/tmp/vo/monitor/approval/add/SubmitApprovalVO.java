package com.ceiec.twmp.tmp.vo.monitor.approval.add;

/**
 * @title: SubmitApprovalVO </br>
 * @createDate: 2019/3/28 17:05 </br>
 * @author: shihsh  </br>
 * @description: 提交监控任务审批 </br>
 * @version: V1.0
 **/


public class SubmitApprovalVO {
    private Long taskId;

    private Byte approvalType;

    private String changeReason;



    public Long getTaskId() {
        return taskId;
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

    public String getChangeReason() {
        return changeReason;
    }

    public void setChangeReason(String changeReason) {
        this.changeReason = changeReason;
    }


}
