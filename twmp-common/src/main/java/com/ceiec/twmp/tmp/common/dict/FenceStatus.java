package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: fence status
 * @create 2019-03-01 16:01
 **/
public enum  FenceStatus {
    /**启用**/
    enable((byte)1, "启用", "dict.fenceStatus.enable"),
    /**禁用**/
    disable((byte)2, "禁用", "dict.fenceStatus.disable");

    /** type of dict **/
    public String type = "fenceStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    FenceStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    FenceStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static FenceStatus get(byte value){
        for(FenceStatus dic:FenceStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
