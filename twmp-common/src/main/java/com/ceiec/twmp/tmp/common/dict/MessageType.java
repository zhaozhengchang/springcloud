package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: message_status
 * @create 2019-03-01 13:38
 **/
public enum MessageType {
    /**告警消息**/
    alarm((byte)1, "告警消息", "dict.messageType.alarm", "a new alarm message"),
    /**通知消息**/
    notice((byte)2, "通知消息", "dict.messageType.notice", "a new notice message"),
    /**分配消息**/
    allocation((byte)3, "分配消息", "", "a new allocation message");

    /** type of dict **/
    public String type = "messageType";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    MessageType(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    MessageType(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static MessageType get(byte value){
        for(MessageType dic:MessageType.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
