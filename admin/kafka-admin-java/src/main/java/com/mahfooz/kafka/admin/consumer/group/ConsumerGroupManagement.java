package com.mahfooz.kafka.admin.consumer.group;

import com.mahfooz.kafka.admin.util.KafkaConfigUtil;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ConsumerGroupManagement {

    private static final String CONSUMER_GROUP="my-first-streams-application";


    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Properties props = KafkaConfigUtil.kafkaProperties("d:/data/messaging/config/kafka-config.properties");
        AdminClient admin = AdminClient.create(props);

        admin.listConsumerGroups().valid().get().forEach(System.out::println);

        Map<TopicPartition, OffsetAndMetadata> offsets =
                admin.listConsumerGroupOffsets(CONSUMER_GROUP)
                        .partitionsToOffsetAndMetadata().get();

        Map<TopicPartition, OffsetSpec> requestLatestOffsets = new HashMap<>();

        for(TopicPartition tp: offsets.keySet()) {
            requestLatestOffsets.put(tp, OffsetSpec.latest());
        }

        Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> latestOffsets =
                admin.listOffsets(requestLatestOffsets).all().get();

        for (Map.Entry<TopicPartition, OffsetAndMetadata> e: offsets.entrySet()) {
            String topic = e.getKey().topic();
            int partition =  e.getKey().partition();
            long committedOffset = e.getValue().offset();
            long latestOffset = latestOffsets.get(e.getKey()).offset();

            System.out.println("Consumer group " + CONSUMER_GROUP
                    + " has committed offset " + committedOffset
                    + " to topic " + topic + " partition " + partition
                    + ". The latest offset in the partition is "
                    +  latestOffset + " so consumer group is "
                    + (latestOffset - committedOffset) + " records behind");
        }

        admin.close(Duration.ofSeconds(60));
    }
}
