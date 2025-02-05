/*

ProducerRecord can have a Key and Partition.
After sending the ProducerRecord, the producer serializes the key and the value objects to ByteArray.
The serialized data is then sent to a partitioner.

public ProducerRecord(@NotNull String topic,
                      K key,
                      V value)

It has four attributes:

    a) topic
    b) partition
    c) key
    d) value

 */
package com.mahfooz.kafka.producer.record;

import java.io.IOException;
import java.util.Properties;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 *
 * @author Mahfooz Alam
 */

public class ProducerRecords {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {
        Properties kafkaProps = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        kafkaProps.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProps.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);
        ProducerRecord<String, String> record = new ProducerRecord<>("json.message","1",
                "{ \"platform\" : \"linux\"" +
                        ",\"uid\" : \"malam\"," +
                        "\"key\" : \"4\"," +
                        "\"value\" : \"1234\"}");
        producer.send(record);
        producer.close();
    }
}
