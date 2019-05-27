package com.ceiec.twmp.tmp.common.dict;

/**
 * @author Ding
 * @version V1.0
 * @Description: online status
 * @create 2019-03-01 15:41
 **/
public enum  OnlineStatus {
    /**在线**/
    online((byte)1, "在线", "dict.onlineStatus.online"),
    /**不在线**/
    offline((byte)2, "不在线", "dict.onlineStatus.offline");
    /** type of dict **/
    public String type = "onlineStatus";
    /** value**/
    public byte value;
    /** directly name **/
    public String name;
    /** name code for i18n **/
    public String nameCode;
    /** comment **/
    public String comment;


    OnlineStatus(byte value, String name, String nameCode) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
    }

    OnlineStatus(byte value, String name, String nameCode, String comment) {
        this.value = value;
        this.name = name;
        this.nameCode = nameCode;
        this.comment = comment;
    }

    public static OnlineStatus get(byte value){
        for(OnlineStatus dic:OnlineStatus.values()){
            if(dic.value==value){
                return dic;
            }
        }
        return null;
    }

}
