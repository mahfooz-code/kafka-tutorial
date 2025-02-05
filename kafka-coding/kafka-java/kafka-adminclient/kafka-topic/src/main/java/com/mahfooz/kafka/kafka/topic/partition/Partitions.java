/*

Topics are additionally broken down into a number of partitions.
Kafka Partitions maintain replica information in Zookeeper.
 */

package com.mahfooz.kafka.kafka.topic.partition;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.KafkaFuture;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Mahfooz Alam
 */
public class Partitions {

    public static void main(String [] args ) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:9092");

        AdminClient client = AdminClient.create(props);

        DescribeTopicsResult result = client.describeTopics(Arrays.asList("test_api"));
        Map<String, KafkaFuture<TopicDescription>> values = result.values();

        KafkaFuture<TopicDescription> topicDescription = values.get("test_api");
        int partitions = topicDescription.get().partitions().size();
        System.out.println(partitions);
    }
}
