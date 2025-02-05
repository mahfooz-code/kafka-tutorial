/*

This is working and tested with kafka-client.

*/
package com.mahfooz.kafka.client.topic.list;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.TopicListing;

public class ListingTopics {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:9092");
        try (AdminClient admin = AdminClient.create(config);) {
            for (TopicListing topicListing : admin.listTopics().listings().get()) {
                System.out.println(topicListing);
            }
        }
    }
}