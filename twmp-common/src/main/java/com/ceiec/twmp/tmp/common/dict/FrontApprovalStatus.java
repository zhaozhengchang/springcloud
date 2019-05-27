package com.ceiec.twmp.tmp.common.dict;

/**
 * @title: FrontApprovalStatus </br>
 * @createDate: 2019/4/1 17:58 </br>
 * @author: shihsh  </br>
 * @description: 前端监控任务审批状态得操作，撤回、驳回、通过 </br>
 * @version: V1.0
 **/


public enum FrontApprovalStatus {
    /**撤回**/
    withdraw((byte)1, "撤回", "dict.frontApprovalStatus.withdraw"),
    /**驳回**/
    refuse((byte)3, "驳回", "dict.frontApprovalStatus.refuse"),
    /**已通过**/
    accept((byte)4, "通过", "dict.frontApprovalStatus.accept");

    /** type of dict **/
    public String type = "frontApprovalStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    FrontApprovalStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    FrontApprovalStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static FrontApprovalStatus get(byte value){
        for(FrontApprovalStatus dic:FrontApprovalStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
