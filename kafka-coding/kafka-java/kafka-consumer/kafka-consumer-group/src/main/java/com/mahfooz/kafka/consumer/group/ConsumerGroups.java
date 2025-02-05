/*

In Kafka, each consumer group is composed of many consumer instances for scalability and fault tolerance.
If all the consumer instances have the same consumer group, then the records will effectively be load balanced over the consumer instances.
When multiple consumers are subscribed to a topic and belong to the same consumer group, each consumer in the group will receive messages
from a different partitions.

Every consumer belongs to a group.
That way they'll share the partitions of a topic.
The main way we scale data consumption from a Kafka topic is by adding more consumers to a consumer group.
It is common for Kafka consumers to do high-latency operations such as write to a database or a time-consuming 
computation on the data.

When multiple consumers are subscribed to a topic and belong to the same consumer group, each consumer in the group will receive messages from a different subset of the partitions in the topic

Consumer groups in Kafka are managed in two places:

    a)  For older consumers, the information is maintained in Zookeeper.
    b)  For the new consumer it is maintained within the Kafka brokers.

 */

package com.mahfooz.kafka.consumer.group;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mahfooz
 */

public class ConsumerGroups {

    public static void main(String[] args) {

        Logger logger = LoggerFactory.getLogger(ConsumerGroups.class.getName());

        String bootstrapServers = "mtmdevhdoped01.intl.vsnl.co.in:9092";
        String groupId = "CountryCounter";
        String topic = "customerCountries";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic));

        Map<String, Integer> customerCountryMap = new HashMap<>();

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
                for (ConsumerRecord<String, String> record : records) {
                    logger.debug("topic = {}, partition = {}, offset = {}, customer = {}, country = {}", record.topic(),
                            record.partition(), record.offset(), record.key(), record.value());
                    int updatedCount = 1;
                    if (customerCountryMap.containsKey(record.value())) {
                        updatedCount = customerCountryMap.get(record.value()) + 1;
                    }
                    customerCountryMap.put(record.value(), updatedCount);
                    JSONObject json = new JSONObject(customerCountryMap);
                    System.out.println(json.toString(4));
                }
            }
        } finally {
            consumer.close();
        }
    }
}