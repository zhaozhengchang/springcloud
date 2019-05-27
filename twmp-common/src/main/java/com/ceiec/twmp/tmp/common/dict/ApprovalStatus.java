package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: approvalStatus
 * @create 2019-03-01 14:53
 **/
public enum ApprovalStatus {
    /**未提交**/
    notSubmitted((byte)1, "未提交", "dict.approvalStatus.notSubmitted"),
    /**已提交**/
    submitted((byte)2, "已提交", "dict.approvalStatus.submitted"),
    /**未通过**/
    notApproval((byte)3, "未通过", "dict.approvalStatus.notApproval"),
    /**已通过**/
    approval((byte)4, "已通过", "dict.approvalStatus.approval");

    /** type of dict **/
    public String type = "approvalStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    ApprovalStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    ApprovalStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static ApprovalStatus get(byte value){
        for(ApprovalStatus dic:ApprovalStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
