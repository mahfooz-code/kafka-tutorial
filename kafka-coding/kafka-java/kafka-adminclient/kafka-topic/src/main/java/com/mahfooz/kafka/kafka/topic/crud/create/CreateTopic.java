/*

a) Using zookeeper

    kafka-topics --zookeeper <zookeeper connect> --create --topic <string> \
    --replication-factor <integer> --partitions <integer>

    kafka-topics --create --zookeeper  10.133.43.94:2181/kafka --replication-factor 1 \
    --partitions 1 --topic test

    kafka-topics --create --zookeeper  localhost:2181 --replication-factor 1 \
    --partitions 1 --topic kafkaconnect

b) Using bootstrap server

    kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test

    kafka-topics--list --bootstrap-server mtmdevhdoped01:9092

    kafka-topics --create --bootstrap-server mtmdevhdoped01:9092 \
        --replication-factor 1 --partitions 1 --topic move-apinotif-statuschangen


Adding partition to a opic

kafka-topics.sh --zookeeper 10.133.43.94:2181/kafka --alter --topic my-topic --partitions 16


kafka-topics.sh --zookeeper 10.133.43.94:2181/kafka --delete --topic my-topic

Listing All Topics in a Cluster

 kafka-topics --zookeeper 10.133.43.94:2181/kafka --list

kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster --describe

kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster--describe --under-replicated-partitions

kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster --describe

 */

package com.mahfooz.kafka.kafka.topic.crud.create;

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
public class CreateTopic {

    private static final String BOOTSTRAP_SERVERS="localhost:19091,localhost:29091,localhost:39091";

    public static void main(String[] args) throws ExecutionException, InterruptedException { 
        String topicName=args[0];
        int numberOfPartitions=Integer.parseInt(args[1]);
        short numberOfReplicationFactor=Short.parseShort(args[2]);

        Properties config = new Properties();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        AdminClient admin = AdminClient.create(config);

        //creating new topic
        System.out.println("-- creating --");
        NewTopic newTopic = new NewTopic(topicName, numberOfPartitions, numberOfReplicationFactor);
        admin.createTopics(Collections.singleton(newTopic));

        //listing
        System.out.println("-- listing --");
        admin.listTopics().names().get().forEach(System.out::println);
    }
}
