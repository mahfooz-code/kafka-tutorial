package com.mahfooz.kafka.producer.modes.acknowledge;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class ProducerAcknowledge {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");
        System.out.println();
    }
}
