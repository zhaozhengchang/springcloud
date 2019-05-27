package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: operateType
 * @create 2019-03-01 15:33
 **/
public enum  OperateType {
    /**新增**/
    insert((byte)1, "新增", "dict.operateType.insert"),
    /**更新**/
    update((byte)2, "更新", "dict.operateType.update"),
    /**删除**/
    delete((byte)3, "删除", "dict.operateType.delete"),
    /**导入**/
    importData((byte)4, "导入", "dict.operateType.importData"),
    /**变更**/
    change((byte)5, "变更", "dict.operateType.change"),
    /**撤回**/
    retract((byte)6, "撤回", "dict.operateType.retract"),
    /**驳回**/
    reject((byte)7, "驳回", "dict.operateType.reject"),
    /**审批通过**/
    approval((byte)8, "审批通过", "dict.operateType.approval"),
    /**变更设备状态**/
    changeDeviceStatus((byte)9, "变更设备状态", "dict.operateType.changeDeviceStatus");
    /** type of dict **/
    public String type = "operateType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    OperateType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    OperateType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static OperateType get(byte value){
        for(OperateType dic:OperateType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
