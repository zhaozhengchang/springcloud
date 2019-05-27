package com.ceiec.twmp.tmp.kafka;


import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import com.ceiec.twmp.tmp.utils.tools.date.DateUtils;

import java.io.Serializable;
import java.util.Date;

/**
 * CreateDate：2018/12/3
 * Author：wenliang
 * Description: kafka response information
 **/
public class KafkaResponse implements Serializable {

    /** serial number */
    private static final long serialVersionUID = -384705772340216229L;

    /** response topic */
    private EKafkaTopic responseTopic;

    /** response message */
    private String responseMsg;

    /** response time */
    private Date responseTime;

    /**
     * default construction function
     */
    KafkaResponse() {

    }

    /**
     * override construction function
     *
     * @param responseTopic response topic string
     * @param responseMsg response topic
     * @param responseTime response message
     */
    KafkaResponse(String responseTopic, String responseMsg, Date responseTime) throws Exception {
        this.responseTopic = EKafkaTopic.getTopicEnum(responseTopic);
        this.responseMsg = responseMsg;
        this.responseTime = responseTime;
    }

    public EKafkaTopic getResponseTopic() {
        return responseTopic;
    }

    public void setResponseTopic(EKafkaTopic responseTopic) {
        this.responseTopic = responseTopic;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public Date getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Date responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
        return "topic:" + responseTopic.getTopic() + " --- message:" + responseMsg + " --- time:" + DateUtils.format(responseTime);
    }
}
