package com.ceiec.twmp.tmp.kafka;

import com.ceiec.twmp.tmp.utils.SpringUtil;
import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.security.JaasUtils;
import org.springframework.kafka.KafkaException;

import java.util.Objects;
import java.util.Properties;

/**
 * CreateDate：2018/12/3
 * Author：wenliang
 * Description: generate kafka sender(producer) or receiver(consumer)
 **/
public class KafkaFactory {

    /**
     * get kafka producer
     *
     * @return kafka message producer
     */
    public static ProducerFactory getKafkaProducer() {
        ProducerFactory producer = SpringUtil.getBean("producerFactory", ProducerFactory.class);
        if (Objects.isNull(producer)) {
            throw new KafkaException("no kafka producer available");
        }
        producer.init();
        return producer;
    }

    /**
     * get kafka consumer
     *
     * @return kafka message consumer
     */
    public static KafkaConsumer getKafkaConsumer(String groupId) {
        ConsumerFactory consumerFactory = SpringUtil.getBean("consumerFactory", ConsumerFactory.class);
        if (Objects.isNull(consumerFactory)) {
            throw new KafkaException("no kafka consumerFactory available");
        }
        return consumerFactory.init(groupId);
    }




}
