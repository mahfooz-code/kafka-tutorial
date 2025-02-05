/*
We send a message to the server and don’t really care if it arrives succesfully or not.
Most of the time, it will arrive successfully, since Kafka is highly available and the producer will retry 
sending messages automatically.
However, some messages will get lost using this method.

 */

package com.mahfooz.kafka.producer.modes.fireforget;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mahfooz Alam
 */
public class FireForget {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {

        String topicName="fire.forget";
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        try (Producer<String, String> producer = new KafkaProducer<>(props);) {
            producer.send(new ProducerRecord<>(topicName, "SUP", "Xyz Pvt Ltd."));
            producer.send(new ProducerRecord<>(topicName, "SUP", "Abc Pvt Ltd."));
            System.out.println("SupplierProducer Completed.");
        }
    }
}
