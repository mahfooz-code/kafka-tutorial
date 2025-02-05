package com.mahfooz.kafka.consumer.offset.manual.combine;

import com.mahfooz.kafka.consumer.serde.supplier.SupplierDeserializer;
import com.mahfooz.kafka.model.Supplier;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ManualCombineSyncConsumerOffset {

    public static void main(String[] args) throws Exception {

        String topicName = "SupplierTopic";
        String groupName = "SupplierTopicGroup";

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:19092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SupplierDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        KafkaConsumer<String, Supplier> consumer = null;

        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(topicName));

            while (true) {
                ConsumerRecords<String, Supplier> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Supplier> record : records) {
                    System.out.println(String.valueOf("Offset = "+record.offset()+", Supplier id= " +
                            ","+record.value().getID()) +
                            ", Supplier  Name = " + record.value().getName() +
                            ", Supplier Start Date = " + record.value().getStartDate().toString());
                }
                consumer.commitAsync();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }
}
