package com.mahfooz.kafka.admin.cluster;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeClusterResult;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ClusterMetadata {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.96:9092");
        AdminClient admin = AdminClient.create(props);

        DescribeClusterResult cluster = admin.describeCluster();

        System.out.println("Connected to cluster " + cluster.clusterId().get());
        System.out.println("The brokers in the cluster are:");
        cluster.nodes().get().forEach(node -> System.out.println("    * " + node));
        System.out.println("The controller is: " + cluster.controller().get());

        admin.close(Duration.ofSeconds(60));

    }
}
