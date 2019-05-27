package com.ceiec.twmp.tmp.common.enums;

/**
 * @author Ding
 * @version V1.0
 * @Description: kafka consumer group
 * @create 2019-03-20 11:47
 **/
public enum  EKafkaGroup {
    /** send gps */
    GROUP_GPS_SEND("group-gps-send", "group for send gps"),
    /** save gps */
    GROUP_GPS_SAVE("group-gps-save", "group for save gps"),
    /** analysis gps in/out fence **/
    GROUP_GPS_ANALYSIS("group-gps-analysis", "group for analysis gps"),
    /** alarm message **/
    GROUP_ALARM_MESSAGE("group-alarm-message", "group for alarm message");

    /** kafka group value */
    private String group;

    /** kafka group description */
    private String desc;

    EKafkaGroup(String group, String desc) {
        this.group = group;
        this.desc = desc;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
