/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.producer.serializers.avro;

import com.mahfooz.kafka.model.Customer;
import com.mahfooz.kafka.producer.serializers.custom.CustomerGenerator;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 *
 * @author Mahfooz Alam
 */
public class AvroProducer {

        public static void main(String[] args) {
                String schemaUrl = "";
                Properties props = new Properties();
                props.put("bootstrap.servers", "localhost:9092");
                props.put("key.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
                props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
                props.put("schema.registry.url", schemaUrl);
                String topic = "customerContacts";

                try (Producer<String, Customer> producer = new KafkaProducer<>(props);) {
                        while (true) {
                                Customer customer = CustomerGenerator.getNext();
                                System.out.println("Generated customer " + customer.toString());
                                ProducerRecord<String, Customer> record = new ProducerRecord<>(topic,
                                                customer.getName(), customer);
                                producer.send(record);
                        }
                }
        }
}
