package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: message_status
 * @create 2019-03-01 11:38
 **/
public enum  MessageStatus {
    /**已读**/
    read((byte)1, "已读", "dict.messageStatus.read"),
    /**未读**/
    unread((byte)2, "未读", "dict.messageStatus.unread");

    /** type of dict **/
    public String type = "messageStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    MessageStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    MessageStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static MessageStatus get(byte value){
        for(MessageStatus dic:MessageStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
