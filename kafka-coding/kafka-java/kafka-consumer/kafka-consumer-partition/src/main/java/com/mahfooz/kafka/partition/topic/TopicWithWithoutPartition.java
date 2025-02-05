package com.mahfooz.kafka.partition.topic;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TopicWithWithoutPartition {

    private static String TOPIC_NAME = "topic-with-2-partition";
    private static int MSG_COUNT = 4;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(TopicWithWithoutPartition::startConsumer);
        executorService.execute(TopicWithWithoutPartition::sendMessages);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

    private static void startConsumer() {
        KafkaConsumer<String, String> consumer = ConsumerConfigUtil.createConsumer(TOPIC_NAME);
        int numMsgReceived = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(2));
            for (ConsumerRecord<String, String> record : records) {
                numMsgReceived++;
                System.out.printf("consumed: key = %s, value = %s, partition id= %s, offset = %s%n",
                        record.key(), record.value(), record.partition(), record.offset());
            }
            consumer.commitSync();
            if (numMsgReceived == MSG_COUNT) {
                break;
            }
        }
    }

    private static void sendMessages() {
        KafkaProducer producer = ConsumerConfigUtil.createProducer();
        for (int i = 0; i < MSG_COUNT; i++) {
            String value = "message-" + i;
            String key = Integer.toString(i);
            System.out.printf("Sending message topic: %s, key: %s, value: %s, partition id: %s%n",
                    TOPIC_NAME, key, value, "Not specified");
            producer.send(new ProducerRecord<>(TOPIC_NAME, key, value));
        }
    }
}