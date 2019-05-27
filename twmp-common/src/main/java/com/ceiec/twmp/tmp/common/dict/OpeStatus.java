package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-03-01 15:48
 **/
public enum  OpeStatus {

    /**初始化**/
    initial((byte)1, "初始化", "dict.opeStatus.initial"),
//    /**安装中**/
//    installing((byte)2, "安装中", "dict.opeStatus.installing"),
    /**已安装**/
    installed((byte)3, "已安装", "dict.opeStatus.installed"),
//    /**拆机中**/
//    dismantling((byte)4, "拆机中", "dict.opeStatus.dismantling"),
    /**报废**/
    scrapped((byte)5, "报废", "dict.opeStatus.scrapped"),
    /**丢失**/
    lost((byte)6, "丢失", "dict.opeStatus.lost");
    
    /** type of dict **/
    public String type = "opeStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    OpeStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    OpeStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static OpeStatus get(byte value){
        for(OpeStatus dic:OpeStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
