package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: distribute type
 * @create 2019-03-01 15:54
 **/
public enum  DistributeType {
    /**自动**/
    automatic((byte)1, "自动", "dict.distributeType.automatic"),
    /**手动**/
    manually((byte)2, "手动", "dict.distributeType.manually");

    /** type of dict **/
    public String type = "distributeType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    DistributeType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    DistributeType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static DistributeType get(byte value){
        for(DistributeType dic:DistributeType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
