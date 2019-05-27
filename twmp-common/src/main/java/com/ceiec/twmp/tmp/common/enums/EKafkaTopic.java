package com.ceiec.twmp.tmp.common.enums;

/**
 * @author Ding
 * @version V1.0
 * @Description: enum topic type of kafka
 * @create 2019-03-15 11:10
 **/
public enum  EKafkaTopic {

    /** topic for sending message from background to crawler */
    TOPIC_GPS("topic-gps", "topic for send gps"),
    /** topic for sending message from crawler to background */
    TOPIC_ALARM_MESSAGE("topic-alarm-message", "topic for send alarm message");

    /** kafka topic value */
    private String topic;

    /** kafka topic description */
    private String desc;

    EKafkaTopic(String topic, String desc) {
        this.topic = topic;
        this.desc = desc;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static EKafkaTopic getTopicEnum(String topic) throws Exception {
        for (EKafkaTopic topicEnum : EKafkaTopic.values()) {
            if (topicEnum.topic.equals(topic)) {
                return topicEnum;
            }
        }
        throw new Exception("illegal kafka topic: " + topic);
    }


}
