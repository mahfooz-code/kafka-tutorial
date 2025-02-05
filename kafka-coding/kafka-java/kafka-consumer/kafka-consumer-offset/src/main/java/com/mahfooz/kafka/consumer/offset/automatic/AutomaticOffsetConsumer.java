/*

For a consumer, we can enable auto commit by setting enable.auto.commit property to true.
The default is true. In that case the consumer's offset will be periodically committed in the background.
If this property is set to false then no offsets are committed.

For a consumer, the property auto.commit.interval.ms, specifies the frequency in milliseconds that the 
consumer offsets are auto-committed to Kafka if enable.auto.commit is set to true.

*/
package com.mahfooz.kafka.consumer.offset.automatic;

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

public class AutomaticOffsetConsumer {

    public static void main(String[] args) {

        String topicName = "automatic";
        String groupName = "offset-auto";

        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091,localhost:29091,localhost:39091");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SupplierDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");

        KafkaConsumer<String, Supplier> consumer = null;

        try {
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Arrays.asList(topicName));

            while (true) {
                ConsumerRecords<String, Supplier> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Supplier> record : records) {
                    System.out.print("Supplier id= " + String.valueOf(record.value().getID()) +
                            " Supplier  Name = " + record.value().getName() +
                            " Supplier Start Date = " + record.value().getStartDate().toString());
                    System.out.print(", Offset="+record.offset()+", partition "+record.partition()+"\n");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.commitSync();
            consumer.close();
        }
    }
}
