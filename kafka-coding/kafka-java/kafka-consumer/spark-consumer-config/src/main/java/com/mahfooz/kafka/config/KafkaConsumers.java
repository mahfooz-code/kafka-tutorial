/*
bin/kafka-console-consumer.sh --zookeeper 10.133.43:94:2181 —topic move-apinotif-statuschange --from-beginning

bin/kafka-console-consumer.sh --zookeeper localhost:2181 —topic Hello-Kafka --from-beginning

 */
package com.mahfooz.kafka.config;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 *
 * @author Mahfooz Alam
 */
public class KafkaConsumers {

    public static void main(String[] args) {

        String consumerGroup = "move";
        String topicName = "positions";

        // Set up client Java properties
        Properties props = ConsumerConfigUtil.getConsumerProps(consumerGroup);

        // Just a user-defined string to identify the consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);

        // Enable auto offset commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            // List of topics to subscribe to
            consumer.subscribe(Collections.singleton(topicName));
            while (true) {
                try {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                    for (ConsumerRecord<String, String> record : records) {
                        System.out.printf("Offset = %d%n", record.offset());
                        System.out.printf("Key = %s%n", record.key());
                        System.out.printf("Value = %s%n", record.value());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
