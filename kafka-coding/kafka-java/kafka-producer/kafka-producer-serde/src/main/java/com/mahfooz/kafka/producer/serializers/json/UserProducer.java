/*

 */
package com.mahfooz.kafka.producer.serializers.json;

import com.mahfooz.kafka.model.User;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.IOException;
import java.util.Properties;

public class UserProducer {

    public static void main(String[] args) throws IOException {
        String topicName = args[0];
        Properties props=new Properties();
        props.load(ClassLoader.getSystemResourceAsStream("producer_connection.properties"));
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                UserSerializer.class.getCanonicalName());
        User user=new User("Mahfooz",18);
        try (Producer<String, User> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<String, User>(topicName, user));
            System.out.println("Message " + user.toString() + " sent !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}