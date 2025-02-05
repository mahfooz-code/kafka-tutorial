/*

 */
package com.mahfooz.kafka.consumer.serde.custom;

import com.mahfooz.kafka.consumer.serde.supplier.SupplierDeserializer;
import com.mahfooz.kafka.model.Supplier;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class SupplierConsumer {

    private static Logger log = LoggerFactory.getLogger(SupplierConsumer.class);

    public static void main(String[] args) {

        String topicName = args[0];
        // Set up client Java properties
        Properties props = new Properties();
        KafkaConsumer<String, Supplier> consumer=null;
        try {
            props.load(ClassLoader.getSystemResourceAsStream("consumer_connection.properties"));
            props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                    SupplierDeserializer.class.getCanonicalName());
            consumer = new KafkaConsumer<>(props);
            // List of topics to subscribe to
            consumer.subscribe(Arrays.asList(topicName));
            while (true){
                ConsumerRecords<String, Supplier> records = consumer.poll(Duration.ofSeconds(100));
                for (ConsumerRecord<String, Supplier> record : records){
                    System.out.println("Supplier id= " + String.valueOf(record.value().getID()) +
                            " Supplier  Name = " + record.value().getName() +
                            " Supplier Start Date = " + record.value().getStartDate().toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}