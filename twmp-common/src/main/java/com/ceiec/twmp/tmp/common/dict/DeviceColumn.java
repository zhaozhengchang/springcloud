package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-04-02 9:50
 **/
public enum DeviceColumn {

    /**显示在首页**/
    show((byte)1, "", "" ),
    /**不显示在首页**/
    noShow((byte)0, "", "" );

    /** type of dict **/
    public String type = "deviceColumn";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    DeviceColumn(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    DeviceColumn(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static DeviceColumn get(byte value){
        for(DeviceColumn dic:DeviceColumn.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
