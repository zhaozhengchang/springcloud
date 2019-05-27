package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: alarm status
 * @create 2019-03-01 15:52
 **/
public enum  AlarmStatus {
    /**未分配**/
    undistributed((byte)1, "未分配", "dict.alarmStatus.undistributed"),
    /**待处置**/
    toDispose((byte)2, "待处置", "dict.alarmStatus.toDispose"),
    /**处置完成**/
    disposed((byte)3, "处置完成", "dict.alarmStatus.disposed");

    /** type of dict **/
    public String type = "alarmStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    AlarmStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    AlarmStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static AlarmStatus get(byte value){
        for(AlarmStatus dic:AlarmStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
