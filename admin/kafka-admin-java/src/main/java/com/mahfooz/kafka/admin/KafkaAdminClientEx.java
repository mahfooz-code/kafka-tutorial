package com.mahfooz.kafka.admin;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class KafkaAdminClientEx {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:29092");
        AdminClient admin = AdminClient.create(props);

        ListTopicsResult topics = admin.listTopics();
        topics.names().get().forEach(System.out::println);

        admin.close(Duration.ofSeconds(30));
    }
}
