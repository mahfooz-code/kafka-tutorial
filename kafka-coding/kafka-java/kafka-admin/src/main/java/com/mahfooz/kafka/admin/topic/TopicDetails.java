package com.mahfooz.kafka.admin.topic;

import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TopicDetails {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092");
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "500000");
        String topicName=args[0];
        try (AdminClient admin = AdminClient.create(config);) {
            DescribeTopicsResult describeTopicsResult= admin.describeTopics(Collections.singleton(topicName));
            describeTopicsResult.all().get().forEach((name,topic)->{
                System.out.println(topic.name()+" "+topic.partitions().size());
                topic.partitions().forEach(partition->{
                    System.out.println("partition: "+partition);
                    System.out.println("isr: "+partition.isr());
                    System.out.println("leader: "+partition.leader());
                    System.out.println("replicas: "+partition.replicas());
                });
                System.out.println("Is internal? "+topic.isInternal());

            });
        }
    }
}
