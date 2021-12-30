package com.mahfooz.kafka.admin.topic.replicas;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ElectionNotNeededException;
import org.apache.kafka.common.errors.NoReassignmentInProgressException;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class JavaReassigningReplicas {

    private static final String TOPIC_NAME="users";
    private static final List<String> TOPIC_LIST=Arrays.asList(new String[]{"users"});

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.94:29092");
            AdminClient admin = AdminClient.create(props);

            Map<TopicPartition, Optional<NewPartitionReassignment>> reassignment = new HashMap<>();
            reassignment.put(new TopicPartition(TOPIC_NAME, 0),
                    Optional.of(new NewPartitionReassignment(Arrays.asList(0, 1))));

            reassignment.put(new TopicPartition(TOPIC_NAME, 1),
                    Optional.of(new NewPartitionReassignment(Arrays.asList(1))));

            reassignment.put(new TopicPartition(TOPIC_NAME, 2),
                    Optional.of(new NewPartitionReassignment(Arrays.asList(1, 0))));

            reassignment.put(new TopicPartition(TOPIC_NAME, 3), Optional.empty());

            admin.alterPartitionReassignments(reassignment).all().get();

            System.out.println("currently reassigning: " +
                    admin.listPartitionReassignments().reassignments().get());

            DescribeTopicsResult demoTopic = admin.describeTopics(TOPIC_LIST);

            TopicDescription topicDescription = demoTopic.values().get(TOPIC_NAME).get();

            System.out.println("Description of demo topic:" + topicDescription);

            admin.close(Duration.ofSeconds(60));
        } catch (ExecutionException | InterruptedException e) {
            if (e.getCause() instanceof ElectionNotNeededException) {
                System.out.println("All leaders are preferred already");
            } else if (e.getCause() instanceof NoReassignmentInProgressException){
                System.out.println("No reassignment");
            }
        }
    }
}
