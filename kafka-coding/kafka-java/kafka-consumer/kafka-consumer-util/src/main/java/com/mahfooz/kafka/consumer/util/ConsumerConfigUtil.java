package com.mahfooz.kafka.consumer.util;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;

import java.util.Arrays;
import java.util.Properties;

public class ConsumerConfigUtil {

    public static final String BROKERS = "localhost:19092,localhost:29092";
    
    public static Properties getProducerProps() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BROKERS);
        props.put("acks", "all");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }

    public static Properties getConsumerProps(String groupName) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", BROKERS);
        props.setProperty("group.id", groupName);
        props.setProperty("enable.auto.commit", "false");
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static Properties getConsumerProps(boolean autoCommit, Long autoCommitMillisInterval) {
        Properties props = new Properties();
        props.setProperty("bootstrap.servers", BROKERS);
        props.setProperty("group.id", "testGroup");
        props.setProperty("enable.auto.commit", Boolean.toString(autoCommit));
        if (autoCommit) {
            props.setProperty("auto.commit.interval.ms", Long.toString(autoCommitMillisInterval));
        }
        props.setProperty("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

    public static KafkaProducer<String,String> createProducer() {
        return new KafkaProducer<>(getProducerProps());
    }

    public static KafkaConsumer<String, String> createConsumer(String topicName) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProps(false,0L));
        consumer.subscribe(Arrays.asList(topicName));
        return consumer;
    }
}
