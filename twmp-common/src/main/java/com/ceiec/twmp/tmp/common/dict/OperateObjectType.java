package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: operate object type
 * @create 2019-03-01 15:24
 **/
public enum  OperateObjectType {
    /**设备**/
    device((byte)1, "设备", "dict.operateObjectType.device"),
    /**被监控人**/
    person((byte)2, "被监控人", "dict.operateObjectType.person"),
    /**组织机构**/
    department((byte)3, "组织机构", "dict.operateObjectType.department"),
    /**监控任务**/
    task((byte)4, "监控任务", "dict.operateObjectType.task"),
    /**文书**/
    paperwork((byte)5, "文书", "dict.operateObjectType.paperwork"),
    /**系统参数**/
    parameter((byte)6, "系统参数", "dict.operateObjectType.parameter"),
    /**审批**/
    approval((byte)7, "审批", "dict.operateObjectType.approval"),
    /**用户**/
    user((byte)8, "用户", "dict.operateObjectType.user");
    /** type of dict **/
    public String type = "operateObjectType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    OperateObjectType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    OperateObjectType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static OperateObjectType get(byte value){
        for(OperateObjectType dic:OperateObjectType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
