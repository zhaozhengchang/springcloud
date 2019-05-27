package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: gps valid
 * @create 2019-03-01 15:19
 **/
public enum  GpsValid {
    /**有效**/
    valid((byte)1, "有效", "dict.gpsValid.valid"),
    /**无效**/
    invalid((byte)0, "无效", "dict.gpsValid.invalid");

    /** type of dict **/
    public String type = "gpsValid";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    GpsValid(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    GpsValid(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static GpsValid get(byte value){
        for(GpsValid dic:GpsValid.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
