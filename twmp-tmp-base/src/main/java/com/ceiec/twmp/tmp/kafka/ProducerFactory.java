package com.ceiec.twmp.tmp.kafka;

import com.ceiec.twmp.tmp.common.enums.EKafkaTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Properties;

/**
 * CreateDate：2018/12/3
 * Author：wenliang
 * Description: send message to kafka server
 **/
@Component
public class ProducerFactory {

    /** kafka producer */
    private org.apache.kafka.clients.producer.KafkaProducer<String, String> producer;

    /** config param environment */
    @Autowired
    private Environment env;

    /** logger */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * disable the new() function
     */
    private ProducerFactory() {

    }

    /**
     * init kafka producer instance
     */
    void init() {
        if (Objects.isNull(producer)) {
            Properties props = new Properties();
            props.put("bootstrap.servers", env.getProperty("spring.kafka.bootstrap-servers"));
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("acks", env.getProperty("spring.cloud.stream.kafka.binder.required-acks"));
            producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);

        }
    }

    /**
     * send single message to kafka server
     *
     * @param topic topic of the message
     * @param message message in json format
     */
    public void sendMsg(EKafkaTopic topic, String message, int partitionNum) {
        try{
            ProducerRecord<String, String> keyedMessage = new ProducerRecord<>(topic.getTopic(), partitionNum, null, message);
            producer.send(keyedMessage);
        }catch (Exception e){
            logger.error("kafka send topic is="+topic.getTopic()+" and message is="+message+" ! error="+e.getMessage());
        }

    }


    public void sendMsg(EKafkaTopic topic, String message) {
        try{
            ProducerRecord<String, String> keyedMessage = new ProducerRecord<>(topic.getTopic(), null, message);
            producer.send(keyedMessage);
        }catch (Exception e){
            logger.error("kafka send topic is="+topic.getTopic()+" and message is="+message+" ! error="+e.getMessage());
        }

    }

}
