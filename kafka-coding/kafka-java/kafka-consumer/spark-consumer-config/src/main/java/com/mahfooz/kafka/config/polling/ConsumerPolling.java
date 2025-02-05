/*

It involves fetching (polling) the server for more data, from subscribed topics using the poll() method.
The poll loop handles coordination between consumers in the consumer group, partition rebalancing, heartbeats
and fetching data from servers.
The poll() method returns data from the topic partitions assigned to it.

At the heart of the consumer API is a simple loop for polling the server for more data.
Once the consumer subscribes to topics, the poll loop handles all details of coordination, partition rebalances,
heartbeats, and data fetching, leaving the developer with a clean API that simply returns available data from the
assigned partitions.

 */
package com.mahfooz.kafka.config.polling;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.regex.Pattern;

public class ConsumerPolling {

    public static void main(String[] args) {
        /*
        // Set up client Java properties
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "10.133.43.94:9092,10.133.43.96:9092,10.133.43.97:9092");

        // Just a user-defined string to identify the consumer group
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroup);

        // Enable auto offset commit
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class.getName());

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {

            // List of topics to subscribe to
            consumer.subscribe(Pattern.compile("test"));
            try {
                while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(100));
                    for (ConsumerRecord<String, String> record : records) {
                        log.debug("topic = %s, partition = %d, offset = %d,customer = %s, country = %s\n",
                                record.topic(), record.partition(),
                                record.offset(), record.key(), record.value());

                        int updatedCount = 1;
                        if (custCountryMap.countainsKey(record.value())) {
                            updatedCount = custCountryMap.get(record.value()) + 1;
                        }
                        custCountryMap.put(record.value(), updatedCount);
                        JSONObject json = new JSONObject(custCountryMap);
                        System.out.println(json.toString(4));
                    }
                }
            } finally {
                consumer.close();
            }
        }*/
    }
}
