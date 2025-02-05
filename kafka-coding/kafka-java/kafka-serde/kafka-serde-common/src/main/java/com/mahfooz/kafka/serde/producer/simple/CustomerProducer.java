/*


java \
    -classpath /app/cloudera/opt/cloudera/parcels/CDH/lib/kafka/libs/<star>:kafka-serde-common-1.0-jar-with-dependencies.jar \
    com.mahfooz.kafka.serde.producer.simple.CustomerProducer test_api




 */
package com.mahfooz.kafka.serde.producer.simple;

import com.mahfooz.kafka.model.Customer;
import com.mahfooz.kafka.serde.serializer.simple.CustomerSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomerProducer {

    public static void main(String[] args) {

        String topicName=args[0];
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"10.133.43.94:9092,10.133.43.96:9092,10.133.43.97:9092");
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                CustomerSerializer.class.getCanonicalName());
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getCanonicalName());

        props.put(ProducerConfig.ACKS_CONFIG,"1");

        Customer customer=new Customer(21,"Mahfooz Alam");

        try (Producer<String, Customer> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<String, Customer>(topicName, customer));
            System.out.println("Message " + customer.getID() +"," +customer.getName()+ " sent !!");
        }
    }
}