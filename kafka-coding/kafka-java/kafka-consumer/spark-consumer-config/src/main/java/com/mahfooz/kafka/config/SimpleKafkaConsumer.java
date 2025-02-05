package com.mahfooz.kafka.config;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.json.JSONObject;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;

public class SimpleKafkaConsumer {

    private static Map<String,Integer> custCountryMap=new HashMap<>();

    public static void main(String[] args) {
        Properties properties= ConsumerConfigUtil.getConsumerProps("simple");
        properties.setProperty("key.deserializer", IntegerDeserializer.class.getCanonicalName());
        try (KafkaConsumer<Integer, String> consumer = new KafkaConsumer<>(properties)) {

            // List of topics to subscribe to
            consumer.subscribe(Pattern.compile("users"));
            try {
                while (true) {
                    ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(100));
                    for (ConsumerRecord<Integer, String> record : records) {
                        System.out.printf("topic = %s, partition = %d, offset = %d,userid = %s, username = %s\n",
                                record.topic(), record.partition(),
                                record.offset(), record.key(), record.value());

                        int updatedCount = 1;
                        if (custCountryMap.containsKey(record.value())) {
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
        }
    }
}
