/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mahfooz.kafka.producer.record.metadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 *
 * @author Mahfooz Alam
 */
public class RecordMetadatas {

    public static void main(String[] args) {

        Properties kafkaProps = new Properties();

        kafkaProps.put("bootstrap.servers", " 10.133.43.96:9092, 10.133.43.94:9092, 10.133.43.97:9092");
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps)) {
            ProducerRecord<String, String> record = new ProducerRecord<>("test", "key", "value");
            RecordMetadata metadata = producer.send(record).get();
            System.out.println(metadata.offset() + "\n" + metadata.partition());
        } catch (ExecutionException ex) {
            Logger.getLogger(RecordMetadatas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
