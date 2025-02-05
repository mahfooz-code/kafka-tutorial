package com.mahfooz.kafka.consumer;

import com.mahfooz.kafka.consumer.serde.supplier.SupplierDeserializer;
import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import com.mahfooz.kafka.model.Supplier;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SyncConsumer {

    public static void main(String[] args) {

        Properties properties= ConsumerConfigUtil.getConsumerProps("mode.sync");
        String topicName="sync.mode";
        properties.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.sync");

        // Enable auto offset commit
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SupplierDeserializer.class.getName());
        KafkaConsumer<String, Supplier> consumer = new KafkaConsumer<>(properties);

        // List of topics to subscribe to
        consumer.subscribe(Arrays.asList(topicName));
        try {
            while (true) {
                ConsumerRecords<String, Supplier> records = consumer.poll(Duration.ofSeconds(100));
                for (ConsumerRecord<String, Supplier> record : records) {
                    System.out.printf("Offset = %d%n", record.offset());
                    System.out.printf("partition = %d%n", record.partition());
                    System.out.printf("Key    = %s%n", record.key());
                    System.out.printf("Value  = %s%n", record.value().getName());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
