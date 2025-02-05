/*

We send a message, the send() method returns a Future object, and we use get() to wait on the future and see if
the send() was successful or not.

 */
package com.mahfooz.kafka.producer.modes.sync;

import com.mahfooz.kafka.model.Supplier;
import com.mahfooz.kafka.producer.serializers.custom.SupplierSerializer;
import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class SyncProducer {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws Exception {

        String topicName = "sync.mode";
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", SupplierSerializer.class.getName());

        try (Producer<String, Supplier> producer = new KafkaProducer<>(props);) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Supplier sp1 = new Supplier(101, "Xyz Pvt Ltd.", df.parse("2016-04-01"));
            Supplier sp2 = new Supplier(102, "Abc Pvt Ltd.", df.parse("2012-01-01"));

            producer.send(new ProducerRecord<>(topicName, "SUP", sp1)).get();
            producer.send(new ProducerRecord<>(topicName, "SUP", sp2)).get();

            System.out.println("SupplierProducer Completed.");
        }
    }
}