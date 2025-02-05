/*

bin/kafka-topics --list --zookeeper localhost:2181

 */

package com.mahfooz.kafka.admin.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Mahfooz Alam
 */
public class ListTopic {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091,localhost:29091,localhost:39091");
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        try (AdminClient admin = AdminClient.create(config);) {
            for (TopicListing topicListing : admin.listTopics().listings().get()) {
                System.out.println(topicListing);
            }
        }
    }
}
