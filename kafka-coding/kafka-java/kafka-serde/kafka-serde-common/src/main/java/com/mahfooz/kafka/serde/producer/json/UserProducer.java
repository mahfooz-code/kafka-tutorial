/*


java \
    -classpath /app/cloudera/opt/cloudera/parcels/CDH/lib/kafka/libs/<star>:kafka-serde-common-1.0-jar-with-dependencies.jar \
    com.mahfooz.kafka.serde.producer.json.UserProducer test-api

 */
package com.mahfooz.kafka.serde.producer.json;

import com.mahfooz.kafka.model.User;
import com.mahfooz.kafka.serde.serializer.json.UserSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class UserProducer {

    public static void main(String[] args) throws IOException {

        String topicName=args[0];
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.133.43.94:9092,10.133.43.96:9092,10.133.43.97:9092");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                UserSerializer.class.getCanonicalName());
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getCanonicalName());

        User user=new User("Mahfooz",32);

        try (Producer<String, User> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<String, User>(topicName, user));
            System.out.println("Message " + user.toString() + " sent !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}