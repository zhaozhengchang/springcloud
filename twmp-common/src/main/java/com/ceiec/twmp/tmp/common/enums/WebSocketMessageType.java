package com.ceiec.twmp.tmp.common.enums;

public enum WebSocketMessageType {
    /**gps message**/
    gps((byte)1, "for push gps"),
    /**alarm message**/
    newAlarm((byte)2, "for push new alarm"),
    /**notice message**/
    newNotice((byte)3, "for push new notice"),
    /**allocation message**/
    newAllocation((byte)4, "for push someone allocation a new alarm");

    /** value**/
    public byte value;
    /**  desc **/
    public String desc;

    WebSocketMessageType(byte value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
