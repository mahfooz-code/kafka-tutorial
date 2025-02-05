/*

The kafka-topics.sh tool provides easy access to most topic operations (configuration changes have been 
deprecated and moved to the kafka-configs.sh tool)

It allows you to create, modify, delete, and list information about topics in the cluster. 

To use this command, you are required to provide the Zookeeper connect string for the cluster with the 
--zookeeper argument. 
In the examples that follow, the Zookeeper connect string is assumed to be zoo1.example.com:2181/kafka-cluster.


 */

package com.mahfooz.kafka.admin.config;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Mahfooz Alam
 */
public class ListKafkaConfig {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties config = new Properties();

        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091,localhost:29091,localhost:39091");
        config.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "500000");

        try (AdminClient admin = AdminClient.create(config);) {

            for (Node node : admin.describeCluster().nodes().get()) {
                System.out.println("-- node: " + node.id() + " --");
                ConfigResource cr = new ConfigResource(ConfigResource.Type.BROKER, "0");
                DescribeConfigsResult dcr = admin.describeConfigs(Collections.singleton(cr));
                dcr.all().get().forEach((k, c) -> {
                    c.entries().forEach(
                            configEntry -> System.out.println(configEntry.name() + "= " + configEntry.value()));
                });
            }
        }
    }
}
