package com.mahfooz.kafka.broker.details;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class BrokerDetails {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091");
        properties.setProperty(AdminClientConfig.CLIENT_ID_CONFIG, BrokerDetails.class.getSimpleName());
        properties.setProperty(AdminClientConfig.METADATA_MAX_AGE_CONFIG, "3000");
        AdminClient kafkaAdminClient  = AdminClient.create(properties);
        DescribeClusterResult result = kafkaAdminClient.describeCluster();
        KafkaFuture<Collection<Node>> nodes = result.nodes();
        Collection<Node> nodeCollections=nodes.get();
        for(Node node:nodeCollections){
            System.out.println(node.id()+","+node.host()+","+node.port()+","+node.rack());
        }
    }
}
