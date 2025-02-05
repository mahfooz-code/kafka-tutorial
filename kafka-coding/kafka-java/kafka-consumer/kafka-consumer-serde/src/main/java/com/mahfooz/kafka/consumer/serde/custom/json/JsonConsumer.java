package com.mahfooz.kafka.consumer.serde.custom.json;

import com.mahfooz.kafka.consumer.serde.user.UserDeserializer;
import com.mahfooz.kafka.model.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class JsonConsumer {

    public static void main(String[] args) throws IOException {

        String topicName="move-apinotif-statuschangen";
        Properties props=new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("consumer_connection.properties"));
        props.setProperty(ConsumerConfig.CLIENT_ID_CONFIG,JsonConsumer.class.getSimpleName());
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getCanonicalName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                UserDeserializer.class.getCanonicalName());
        try (KafkaConsumer<String, User> consumer = new KafkaConsumer<>(props)) {
            consumer.subscribe(Collections.singletonList(topicName));
            while (true) {
                ConsumerRecords<String, User> messages = consumer.poll(Duration.ofSeconds(100));
                for (ConsumerRecord<String, User> message : messages) {
                    System.out.println("Message received " + message.value().toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}