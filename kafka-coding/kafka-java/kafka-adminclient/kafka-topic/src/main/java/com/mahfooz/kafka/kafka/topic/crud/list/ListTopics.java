/*

kafka-topics --zookeeper 10.133.43.94:2181/kafka --list

kafka-topics --zookeeper 10.133.43.94:2181/kafka --topic test-api --describe

kafka-topics --zookeeper 10.133.43.94:2181/kafka --describe --under-replicated-partitions

kafka-topics --bootstrap-servers 10.133.43.94:9092 --topic test-api --describe

Below java program is working.

 */

package com.mahfooz.kafka.kafka.topic.crud.list;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author Mahfooz Alam
 */
public class ListTopics {
    private static final String BOOTSTRAP_SERVERS="localhost:19091,localhost:29091,localhost:39091";
    public static void main(String[] args) {
        try {
            Properties config = new Properties();
            config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
            config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
            AdminClient client = AdminClient.create(config);
            ListTopicsResult ltr = client.listTopics();

            //Getting names of topic
            KafkaFuture<Set<String>> names = ltr.names();
            //KafkaFuture<Map<String, TopicListing>> namesToTopicList=ltr.namesToListings();
            System.out.println(names.get());

            //System.out.println(names.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
