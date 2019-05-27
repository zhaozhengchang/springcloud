package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: fence type
 * @create 2019-03-01 16:20
 **/
public enum  FenceType {
    /**禁止入内**/
    noEntrance((byte)1, "禁止入内", "dict.fenceType.noEntrance"),
    /**禁止外出**/
    noGoOut((byte)2, "禁止外出", "dict.fenceType.noGoOut");

    /** type of dict **/
    public String type = "fenceType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    FenceType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    FenceType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static FenceType get(byte value){
        for(FenceType dic:FenceType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
