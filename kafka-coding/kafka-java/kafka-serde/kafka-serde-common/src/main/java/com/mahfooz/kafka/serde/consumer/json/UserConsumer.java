/*

 java \
    -classpath /app/cloudera/opt/cloudera/parcels/CDH/lib/kafka/libs/<star>:kafka-serde-common-1.0-jar-with-dependencies.jar \
    com.mahfooz.kafka.serde.consumer.json.UserConsumer test-api

 */
package com.mahfooz.kafka.serde.consumer.json;

import com.mahfooz.kafka.model.User;
import com.mahfooz.kafka.serde.deserializer.json.UserDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class UserConsumer {

    public static void main(String[] args) throws IOException {

        String topicName = args[0];
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.133.43.94:9092,10.133.43.96:9092,10.133.43.97:9092");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                UserDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.GROUP_ID_CONFIG,"kafka-serde");

        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getCanonicalName());

        try (KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(topicName));
            while (true) {
                ConsumerRecords<String, User> messages = consumer.poll(Duration.ofSeconds(100));
                for (ConsumerRecord<String, User> message : messages) {
                    System.out.println("Message received " + message.value().toString());
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage()+" , "+e.getStackTrace());
            e.printStackTrace();
        }
    }
}
