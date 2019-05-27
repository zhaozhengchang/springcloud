package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: dispose type
 * @create 2019-03-01 15:57
 **/
public enum  DisposeType {
    /**回退**/
    backoff((byte)1, "回退", "dict.disposeType.backoff"),
    /**走访**/
    interview((byte)2, "走访", "dict.disposeType.interview"),
    /**电话访问**/
    telephoneInterview((byte)3, "电话访问", "dict.disposeType.telephoneInterview"),
    /**联系监控小组**/
    contactTaskTeam((byte)4, "联系监控小组", "dict.disposeType.contactTaskTeam"),
    /**其他**/
    other((byte)5, "其他", "dict.disposeType.other");

    /** type of dict **/
    public String type = "disposeType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    DisposeType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    DisposeType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static DisposeType get(byte value){
        for(DisposeType dic:DisposeType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
