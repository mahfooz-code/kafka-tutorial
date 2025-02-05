/*

bin/kafka-topics --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-name

bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1   --partitions 1 --topic Hello-Kafka

 */

package com.mahfooz.kafka.admin.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Mahfooz Alam
 */
public class CreatingTopic {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092");
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");

        try (AdminClient admin = AdminClient.create(config);) {

            // creating new topic
            System.out.println("-- creating --");
            NewTopic newTopic = new NewTopic("prod-consumer", 1, (short) 2);
            admin.createTopics(Collections.singleton(newTopic));

            // listing
            System.out.println("-- listing --");
            admin.listTopics().names().get().forEach(System.out::println);
        }
    }
}
