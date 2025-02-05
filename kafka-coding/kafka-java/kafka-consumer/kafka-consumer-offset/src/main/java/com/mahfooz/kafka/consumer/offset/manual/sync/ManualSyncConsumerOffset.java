/*

Commit Current Offset
If the content of the record is printed, it can be concluded as the processing is completed.

System.out.printf("topic = %s, partition = %s, offset =
      %d, customer = %s, country = %s\n",
         record.topic(), record.partition(),
           record.offset(), record.key(), record.value());
}
try {
After completing the processing of all records in the current batch, commitSync is called to commit the last offset in the batch.

consumer.commitSync();
} catch (CommitFailedException e) {
As long as there is nill error that cannot be recovered, commitSync retries committing. In that case, error will be logged.

log.error("commit failed", e)
}
}

 */
package com.mahfooz.kafka.consumer.offset.manual.sync;

import com.mahfooz.kafka.consumer.serde.supplier.SupplierDeserializer;
import com.mahfooz.kafka.model.Supplier;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class ManualSyncConsumerOffset {

    private static final Logger log= LoggerFactory.getLogger(ManualSyncConsumerOffset.class);

    public static void main(String[] args) {

        String topicName = "SupplierTopic";
        String groupName = "SupplierTopicGroup";

        Properties props = new Properties();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:19092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupName);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, SupplierDeserializer.class.getCanonicalName());
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        KafkaConsumer<String, Supplier> consumer = null;

        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));
        while (true) {
            ConsumerRecords<String, Supplier> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, Supplier> record : records) {
                System.out.printf("topic = %s, partition = %s, offset = %d, key = %s, supplier = %s\n",
                        record.topic(),
                        record.partition(),
                        record.offset(),
                        record.key(),
                        record.value());
            }
            try {
                consumer.commitSync();
            } catch (CommitFailedException e) {
                log.error("commit failed", e);
            }
        }
    }
}
