package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description:
 * @create 2019-03-21 10:32
 **/
public enum  AlarmType {
    /**暴力拆卸告警**/
    violentDismantling((byte)1, "暴力拆卸告警", "dict.alarmType.violentDismantling", (byte)1),
    /**低电量告警**/
    lowPower((byte)2, "低电量告警", "dict.alarmType.lowPower",(byte)4),
    /**越界告警**/
    overFence((byte)3, "越界告警", "dict.alarmType.overFence", (byte)3),
    /**设备离线告警**/
    offline((byte)4, "设备离线告警", "dict.alarmType.offline", (byte)2),
    /**手动创建告警**/
    manuallyCreate((byte)5, "手动创建告警", "dict.alarmType.manuallyCreate", (byte)0),
    /**一键求助告警**/
    sos((byte)6, "一键求助告警", "dict.alarmType.sos", (byte)2);

    /** type of dict **/
    public String type = "alarmType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;
    /** level, level 0 is the most important **/
    public byte level;


    AlarmType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    AlarmType(byte value, String name, String nameCode, byte level) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.level = level;
    }

    AlarmType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static AlarmType get(byte value){
        for(AlarmType dic:AlarmType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }
}
