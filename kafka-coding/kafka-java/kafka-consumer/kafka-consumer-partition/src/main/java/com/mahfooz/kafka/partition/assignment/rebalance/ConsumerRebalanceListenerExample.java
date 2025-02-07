package com.mahfooz.kafka.partition.assignment.rebalance;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ConsumerRebalanceListenerExample {

    public static void main(String[] args) throws InterruptedException {
        String topicName=args[0];
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            executorService.execute(() -> startConsumer("consumer-" + finalI,topicName));
            Thread.sleep(3000);
        }
        executorService.shutdown();
        executorService.awaitTermination(3, TimeUnit.MINUTES);
    }

    private static KafkaConsumer<String, String> startConsumer(String name,String topicName) {
        Properties consumerProps = ConsumerConfigUtil.getConsumerProps(false,0L);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(Collections.singleton(topicName),
                new ConsumerRebalanceListener() {
                    @Override
                    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                        System.out.printf("onPartitionsRevoked - consumerName: %s, partitions: %s%n", name,
                                formatPartitions(partitions));
                    }

                    @Override
                    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                        System.out.printf("onPartitionsAssigned - consumerName: %s, partitions: %s%n", name,
                                formatPartitions(partitions));
                    }
                });
        System.out.printf("starting consumerName: %s%n", name);
        consumer.poll(Duration.ofSeconds(10));
        System.out.printf("closing consumerName: %s%n", name);
        consumer.close();
        return consumer;
    }

    private static List<String> formatPartitions(Collection<TopicPartition> partitions) {
        return partitions.stream().map(topicPartition ->
                String.format("topic: %s, partition: %s", topicPartition.topic(), topicPartition.partition()))
                .collect(Collectors.toList());
    }
}