package com.mahfooz.kafka.admin.topic.partition;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewPartitions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AddingPartitionTopic {

    private static final String TOPIC_NAME="users";
    private static final int NUM_PARTITIONS=5;

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.94:29092");
        AdminClient admin = AdminClient.create(props);

        Map<String, NewPartitions> newPartitions = new HashMap<>();
        newPartitions.put(TOPIC_NAME, NewPartitions.increaseTo(NUM_PARTITIONS+2));
        admin.createPartitions(newPartitions).all().get();

        admin.close(Duration.ofSeconds(60));
    }
}
