package com.ceiec.twmp.tmp.common.dict;

/**
 * @title: ApprovalType </br>
 * @createDate: 2019/3/28 18:00 </br>
 * @author: shihsh  </br>
 * @description: 审批类型 </br>
 * @version: V1.0
 **/


public enum ApprovalType {
    /**新建监控任务审批 **/
    createTaskApproval((byte)1, "新建监控任务审批 ", "dict.approvalType.createTaskApproval"),
    /**结束监控任务的审批**/
    endMonitorTaskApproval((byte)2, "结束监控任务审批", "dict.approvalType.endMonitorTaskApproval"),
    /**设备变更审批**/
    deviceChangeApproval((byte)3, "设备变更审批", "dict.approvalType.deviceChangeApproval"),
    /**监控任务参数变更审批**/
    monitorParameterChangeApproval((byte)4, "监控任务参数变更审批5", "dict.approvalType.monitorParameterChangeApproval"),
    /**终止任务的审批**/
    stopTaskApproval((byte)5, "终止任务审批", "dict.approvalType.stopTaskApproval");

    /** type of dict **/
    public String type = "approvalType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    ApprovalType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    ApprovalType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static ApprovalType get(byte value){
        for(ApprovalType dic:ApprovalType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
