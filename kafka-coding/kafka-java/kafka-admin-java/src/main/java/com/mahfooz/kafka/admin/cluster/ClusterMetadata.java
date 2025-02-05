package com.mahfooz.kafka.admin.cluster;

import com.mahfooz.kafka.admin.util.KafkaConfigUtil;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;

import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ClusterMetadata {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Properties props = KafkaConfigUtil.kafkaProperties("d:/data/messaging/config/kafka-config.properties");
        AdminClient admin = AdminClient.create(props);

        DescribeClusterResult cluster = admin.describeCluster();

        System.out.println("Connected to cluster " + cluster.clusterId().get());
        System.out.println("The brokers in the cluster are:");
        cluster.nodes().get().forEach(node -> System.out.println("    * " + node));
        System.out.println("The controller is: " + cluster.controller().get());

        admin.close(Duration.ofSeconds(60));

    }
}
