package com.mahfooz.kafka.partition.assignment.manual;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PartitionAssignmentManual {

    private static int PARTITION_COUNT = 3;
    private static String TOPIC_NAME = "partition-manual-assign";
    private static  String GROUP_NAME="partition-manual-assign";
    private static int MSG_COUNT = 4;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(PartitionAssignmentManual::startConsumer);
        executorService.execute(PartitionAssignmentManual::sendMessages);
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }

    private static void startConsumer() {
        Properties consumerProps = ConsumerConfigUtil.getConsumerProps(GROUP_NAME);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        //don't call consumer#subscribe() assigning partition-id=1
        consumer.assign(Collections.singleton(new TopicPartition(TOPIC_NAME, 1)));
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
        Properties producerProps = ConsumerConfigUtil.getProducerProps();
        KafkaProducer producer = new KafkaProducer<>(producerProps);;
        for (int i = 0; i < MSG_COUNT; i++) {
            for (int partitionId = 0; partitionId < PARTITION_COUNT; partitionId++) {
                String value = "message-" + i;
                String key = Integer.toString(i);
                System.out.printf("Sending message topic: %s, key: %s, value: %s, partition id: %s%n",
                        TOPIC_NAME, key, value, partitionId);
                producer.send(new ProducerRecord<>(TOPIC_NAME, partitionId, key, value));
            }
        }
    }
}
