/*

Producers are front-end applications, proxy applications and adapters to legacy systems, which produce messages and publish
those messages to Kafka Broker.

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic topic-name

bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka

bin/kafka-console-producer.sh --broker-list localhost:9092 


 kafka-topics --zookeeper mtmdevhdoped01:2181/kafka \
    --create --topic customerCountries \
    --replication-factor 1 \
    --partitions 1 \
    --if-not-exists

 */
package com.mahfooz.kafka.producer.modes;

import java.io.IOException;
import java.util.Properties;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 *
 * @author Mahfooz Alam
 */
public class KafkaProducers {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {

        // Set up client Java properties
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);

        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        try (KafkaProducer<Integer, String> producer = new KafkaProducer<>(props)) {

            for(int i=0;i<10;i++) {
                ProducerRecord<Integer, String> record = new ProducerRecord<>("users", i,
                        "user"+i);
                try {
                    producer.send(record);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
