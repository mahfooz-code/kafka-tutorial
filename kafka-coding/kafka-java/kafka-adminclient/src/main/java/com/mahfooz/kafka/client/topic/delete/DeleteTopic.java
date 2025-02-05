/*

delete.topic.enable = true in config/server.properties of Kafka brokers 

*/
package com.mahfooz.kafka.client.topic.delete;

import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DeleteTopicsResult;

public class DeleteTopic {

    private Properties getProperties() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:9092");
        properties.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        return properties;
    }

    public void deleteTestTopic(String topic) {

        Properties props = getProperties();
        String clientId = Long.toString(new Random().nextLong());
        props.put("client.id", clientId);
        try (AdminClient adminClient = AdminClient.create(props);) {

            DeleteTopicsResult deleteTopicsResult = adminClient.deleteTopics(Arrays.asList(topic));
            while (!deleteTopicsResult.all().isDone()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

    }

    public static void main(String[] args) {
        DeleteTopic deleteTopic = new DeleteTopic();
        deleteTopic.deleteTestTopic("test_api");
    }

}
