/*

The Schema Registry is not part of Apache Kafka but there are several open source options to choose from.
The consumers can then use the identifier to pull the record out of the schema registry and deserialize the data.

 */
package com.mahfooz.kafka.producer.schema.registry;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import com.mahfooz.kafka.model.Customer;

public class SchemaRegistry {

    public static void main(String[] args) {

        String schemaUrl = args[0];
        String topic = args[1];

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", schemaUrl);

        try (Producer<String, Customer> producer = new KafkaProducer<String, Customer>(props);) {

            while (true) {
                Customer customer = new Customer(1, "Mahfooz");
                System.out.println("Generated customer " + customer.toString());
                ProducerRecord<String, Customer> record = new ProducerRecord<>(topic, customer.getName(), customer);
                producer.send(record);
            }
        }
    }
}