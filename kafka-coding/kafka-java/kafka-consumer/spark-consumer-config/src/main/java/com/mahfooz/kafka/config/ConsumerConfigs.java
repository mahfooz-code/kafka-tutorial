/*

zookeeper.connect=127.0.0.1:2181
zookeeper.connect.timeout.ms=60000
group.id=test-consumer-group
consumer.timeout.ms=100
auto.offset.reset=
auto.commit.enable=true
bootstrap.servers
    The first property, bootstrap.servers, is the connection string to a Kafka cluster.


 */
package com.mahfooz.kafka.config;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 *
 * @author mahfooz
 */
public class ConsumerConfigs {

    public static void main(String[] args) {

        // Set up client Java properties
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.94:9092");

        props.put(ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-test");

        // Just a user-defined string to identify the consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // Enable auto offset commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // List of topics to subscribe to
        consumer.subscribe(Arrays.asList("test"));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Offset = %d%n", record.offset());
                    System.out.printf("Key    = %s%n", record.key());
                    System.out.printf("Value  = %s%n", record.value());
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
