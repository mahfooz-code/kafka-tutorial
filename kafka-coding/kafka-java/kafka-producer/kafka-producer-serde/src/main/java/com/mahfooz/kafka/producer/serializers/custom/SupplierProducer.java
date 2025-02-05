package com.mahfooz.kafka.producer.serializers.custom;

import com.mahfooz.kafka.model.Supplier;
import com.mahfooz.kafka.producer.serializers.config.SerdeConf;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Date;
import java.util.Properties;

public class SupplierProducer {

    public static void main(String[] args) {
        String topicName = "automatic";
        Properties props=new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, SerdeConf.BOOTSTRAP_SERVERS);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                SupplierSerializer.class.getCanonicalName());

        Supplier supplier=new Supplier(3,"Mahfooz Alam",new Date());
        try (Producer<String, Supplier> producer = new KafkaProducer<>(props)) {
            producer.send(new ProducerRecord<>(topicName, supplier));
            System.out.println("Message " + supplier.toString() + " sent !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
