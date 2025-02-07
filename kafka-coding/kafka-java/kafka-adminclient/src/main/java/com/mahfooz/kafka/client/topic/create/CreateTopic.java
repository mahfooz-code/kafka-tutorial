package com.mahfooz.kafka.client.topic.create;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateTopic {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:9092");
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        try (AdminClient admin = AdminClient.create(config);) {
            // creating new topic
            System.out.println("-- creating --");
            NewTopic newTopic = new NewTopic("my-new-topic", 1, (short) 1);
            admin.createTopics(Collections.singleton(newTopic));

            // listing
            System.out.println("-- listing --");
            admin.listTopics().names().get().forEach(System.out::println);
        }
    }
}