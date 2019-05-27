package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: type of ope
 * @create 2019-04-11 9:42
 **/
public enum  OpeType {

    /**安装**/
    toInstall((byte)1, "安装", ""),
    /**拆机**/
    toDismantle((byte)2, "拆机", ""),
    /**变更**/
    toChange((byte)3, "变更", "");

    /** type of dict **/
    public String type = "opeType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    OpeType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    OpeType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static OpeType get(byte value){
        for(OpeType dic:OpeType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
