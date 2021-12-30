package com.mahfooz.kafka.admin.topic.leader;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.common.ElectionType;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ElectionNotNeededException;

import java.time.Duration;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class LeaderElection {

    private static final String TOPIC_NAME="users";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.94:29092");
        AdminClient admin = AdminClient.create(props);


        Set<TopicPartition> electableTopics = new HashSet<>();
        electableTopics.add(new TopicPartition(TOPIC_NAME, 0));
        try {
            admin.electLeaders(ElectionType.PREFERRED, electableTopics).all().get();
        } catch (ExecutionException | InterruptedException e) {
            if (e.getCause() instanceof ElectionNotNeededException) {
                System.out.println("All leaders are preferred already");
            }
        }

        admin.close(Duration.ofSeconds(60));
    }
}
