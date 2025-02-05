package com.mahfooz.kafka.admin;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.junit.jupiter.api.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class KafkaAdminClientTest {

    private AdminClient client = null;

    @BeforeEach
    public void setup() {
        Map<String, Object> conf = new HashMap<>();
        conf.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19091,localhost:29091,localhost:39091");
        conf.put(AdminClientConfig.REQUEST_TIMEOUT_MS_CONFIG, "50000");
        client = AdminClient.create(conf);

    }

    @AfterEach
    public void teardown() {
        client.close();
    }

    @Test
    @Order(4)
    public void testNames() throws InterruptedException, ExecutionException {
        ListTopicsResult ltr = client.listTopics();
        KafkaFuture<Set<String>> names = ltr.names();
        System.out.println(names.get());
    }

    @Test
    @Order(2)
    public void testCreateTopic() {
        int partitions = 1;
        short replicationFactor = 1;
        try {
            KafkaFuture<Void> future = client
                    .createTopics(Collections.singleton(new NewTopic("tweet", partitions, replicationFactor)),
                            new CreateTopicsOptions().timeoutMs(10000))
                    .all();
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    @Order(1)
    public void testDeleteTopic() {
        KafkaFuture<Void> future = client.deleteTopics(Collections.singleton("tweet")).all();
        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(3)
    public void testChangeProperties() throws InterruptedException, ExecutionException {
        ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, "tweet");

        // get the current topic configuration
        DescribeConfigsResult describeConfigsResult = client.describeConfigs(Collections.singleton(resource));

        Map<ConfigResource, Config> config;
        config = describeConfigsResult.all().get();

        System.out.println(config);

        // create a new entry for updating the retention.ms value on the same topic
        ConfigEntry retentionEntry = new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, "60000");
        Map<ConfigResource, Config> updateConfig = new HashMap<ConfigResource, Config>();
        updateConfig.put(resource, new Config(Collections.singleton(retentionEntry)));

        AlterConfigsResult alterConfigsResult = client.alterConfigs(updateConfig);
        alterConfigsResult.all();

        describeConfigsResult = client.describeConfigs(Collections.singleton(resource));

        config = describeConfigsResult.all().get();
    }
}
