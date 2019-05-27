package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: lifecycle event type
 * @create 2019-03-01 15:42
 **/
public enum  LifecycleEventType {
    /**新增**/
    insert((byte)1, "新增", "dict.lifecycleEventType.insert", "dict.lifecycle.insert.comment"),
    /**更新**/
    update((byte)2, "更新", "dict.lifecycleEventType.update"),
    /**删除**/
    delete((byte)3, "删除", "dict.lifecycleEventType.delete"),
    /**安装**/
    install((byte)4, "安装", "dict.lifecycleEventType.install"),
    /**拆机**/
    dismantle((byte)5, "拆机", "dict.lifecycleEventType.dismantle"),
    /**报废**/
    scrapped((byte)6, "报废", "dict.lifecycleEventType.scrapped");

    /** type of dict **/
    public String type = "lifecycleEventType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    LifecycleEventType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    LifecycleEventType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static LifecycleEventType get(byte value){
        for(LifecycleEventType dic:LifecycleEventType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }




}
