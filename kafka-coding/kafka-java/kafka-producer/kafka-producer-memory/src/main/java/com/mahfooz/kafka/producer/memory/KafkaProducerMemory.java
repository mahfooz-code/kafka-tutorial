/*

This sets the amount of memory the producer will use to buffer messages waiting to be sent to brokers.
If messages are sent by the application faster than they can be delivered to the server, the producer may run out of
space and additional send() calls will either block or throw an exception, based on
the block.on.buffer.full parameter (replaced with max.block.ms in release 0.9.0.0, which allows blocking for a
certain time and then throwing an exception).

 */
package com.mahfooz.kafka.producer.memory;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class KafkaProducerMemory {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {

        String topicName="producer.memory";
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,33554432);
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        try (Producer<String, String> producer = new KafkaProducer<>(props);) {
            producer.send(new ProducerRecord<>(topicName, "SUP", "Xyz Pvt Ltd."));
            producer.send(new ProducerRecord<>(topicName, "SUP", "Abc Pvt Ltd."));
            System.out.println("SupplierProducer Completed.");
        }
    }
}
