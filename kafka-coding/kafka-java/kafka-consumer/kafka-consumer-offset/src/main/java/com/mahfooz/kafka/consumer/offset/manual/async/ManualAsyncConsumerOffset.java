/*

In case of a manual commit, the application is blocked until the broker responds to the commit request.
In Asynchronous commit, instead of waiting for the broker to respond to a commit, just a request will be sent and continued.
Let's see how asynchronous Commit is used.

    while (true) {
        ConsumerRecords<String, String> records = consumer.poll(100);
        for (ConsumerRecord<String, String> record : records)
        {
            System.out.printf("topic = %s, partition = %s, offset = %d, customer = %s, country = %s\n",
            record.topic(), record.partition(), record.offset(),
            record.key(), record.value());
    }
    Just commit the last offset and carry on
    consumer.commitAsync();
    }
 */
package com.mahfooz.kafka.consumer.offset.manual.async;

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

public class ManualAsyncConsumerOffset {
    public static void main(String[] args) throws Exception {

        String topicName = "automatic";
        String groupName = "offset-manual";

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091,localhost:29091,localhost:39091");
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
                    System.out.print("Supplier id= " + String.valueOf(record.value().getID()) + " Supplier  Name = "
                            + record.value().getName() + " Supplier Start Date = "
                            + record.value().getStartDate().toString());
                    System.out.print(", Offset=" + record.offset() + ", partition " + record.partition() + "\n");
                }
                consumer.commitAsync();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (consumer != null)
                consumer.close();
        }
    }
}
