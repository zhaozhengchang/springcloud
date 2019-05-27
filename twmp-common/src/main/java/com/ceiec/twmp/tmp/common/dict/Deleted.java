package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description:delete flag
 * @create 2019-03-01 11:24
 **/
public enum Deleted {
    /**未删除**/
    noDeleted((byte)1, "未删除", "dict.deleted.noDeleted" ),
    /**已删除**/
    deleted((byte)0, "已删除", "dict.deleted.deleted" );

    /** type of dict **/
    public String type = "deleted";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    Deleted(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    Deleted(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static Deleted get(byte value){
        for(Deleted dic:Deleted.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
