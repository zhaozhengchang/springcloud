package com.ceiec.twmp.tmp.kafka;

import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import org.apache.commons.lang.ArrayUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * CreateDate：2018/12/3
 * Author：wenliang
 * Description: receive & process message from kafka server
 **/
@Component
public class ConsumerFactory {


    /** config param environment */
    @Autowired
    private Environment env;

    /**
     * disable the new() function
     */
    private ConsumerFactory() {

    }

    /**
     * init kafka consumer instance
     */
    public KafkaConsumer<String, String> init(String groupId) {
        Properties props = new Properties();
        props.put("enable.auto.commit", "true");
        props.put("bootstrap.servers", env.getProperty("spring.kafka.bootstrap-servers"));
        props.put("group.id",groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return new KafkaConsumer<>(props);
    }

    /**
     * set topics to be detected
     *
     * @param topics kafka topics
     */
    public static void setConsumerTopic(KafkaConsumer consumer, EKafkaTopic... topics) {
        if (ArrayUtils.isEmpty(topics)) {
            throw new KafkaException("at least one topic is needed");
        }

        //get kafka topics(in String format) need to be detected
        List<String> topicStrs = new ArrayList<>();
        for (EKafkaTopic topic : topics) {
            topicStrs.add(topic.getTopic());
        }
        consumer.subscribe(topicStrs);
    }

}
