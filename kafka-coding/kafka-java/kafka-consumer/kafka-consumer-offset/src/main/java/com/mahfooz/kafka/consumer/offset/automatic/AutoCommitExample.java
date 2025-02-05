/*

*/
package com.mahfooz.kafka.consumer.offset.automatic;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;

public class AutoCommitExample {
    
    private static String TOPIC_NAME = "move-apinotif-asynccallback";
    private static int MSG_COUNT = 4;
  
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(AutoCommitExample::startConsumer);
        //executorService.execute(AutoCommitExample::sendMessages);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
  
    private static void startConsumer() {
        Properties consumerProps = ConsumerConfigUtil.getConsumerProps(false, 1000L);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        TopicPartition topicPartition = new TopicPartition(TOPIC_NAME, 0);
        consumer.assign(Collections.singleton(topicPartition));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("consumed: key = %s, value = %s, partition id= %s, offset = %s%n",
                        record.key(), record.value(), record.partition(), record.offset());
            }
            if (records.isEmpty()) {
                System.out.println("-- terminating consumer --");
                break;
            }
  
            printOffsets(consumer, topicPartition);
        }
        printOffsets(consumer, topicPartition);
    }
  
    private static void printOffsets(KafkaConsumer<String, String> consumer, TopicPartition topicPartition) {
        OffsetAndMetadata offsetAndMetadata = consumer.committed(topicPartition);
        long position = consumer.position(topicPartition);
        System.out.printf("Committed: %s, current position %s%n", offsetAndMetadata == null ? null : offsetAndMetadata
                .offset(), position);
    }
  
    private static void sendMessages() {
        Properties producerProps = ConsumerConfigUtil.getProducerProps();
        KafkaProducer<String,String> producer = new KafkaProducer<>(producerProps);
        for (int i = 0; i < MSG_COUNT; i++) {
            String value = "message-" + i;
            System.out.printf("Sending message topic: %s, value: %s%n", TOPIC_NAME, value);
            producer.send(new ProducerRecord<>(TOPIC_NAME, value));
        }
        producer.flush();
        producer.close();
    }
  }