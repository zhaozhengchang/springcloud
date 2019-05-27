package com.ceiec.twmp.tmp.common.dict;

/**
 * @version V1.0
 * @title: MonitorRequestType </br>
 * @createDate：2019/3/15 16:07 </br>
 * @author：shihsh </br>
 * @description: 监控任务请求类型 </br>
 **/

public enum MonitorRequestType {
    /**添加**/
    add((byte) 1, "添加", "dict.monitorRequestType.add"),
    /**编辑**/
    edit((byte) 2, "编辑", "dict.monitorRequestType.edit"),
    /**参数变更审批**/
    change((byte)3, "参数变更审批", "dict.monitorRequestType.change");


    /** type of dict **/
    public String type = "monitorRequestType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;

    MonitorRequestType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    MonitorRequestType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static MonitorRequestType get(byte value){
        for(MonitorRequestType dic:MonitorRequestType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
