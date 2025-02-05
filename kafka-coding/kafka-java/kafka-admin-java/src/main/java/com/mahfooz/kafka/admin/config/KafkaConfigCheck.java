package com.mahfooz.kafka.admin.config;

import com.mahfooz.kafka.admin.util.KafkaConfigUtil;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;

import java.io.IOException;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class KafkaConfigCheck {

    private static final String TOPIC_NAME="users";

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {

        Properties props = KafkaConfigUtil.kafkaProperties("d:/data/messaging/config/kafka-config.properties");
        AdminClient admin = AdminClient.create(props);

        ConfigResource configResource =
                new ConfigResource(ConfigResource.Type.TOPIC, TOPIC_NAME);

        DescribeConfigsResult configsResult =
                admin.describeConfigs(Collections.singleton(configResource));

        Config configs = configsResult.all().get().get(configResource);

        // print non-default configs
        configs.entries().stream().filter(
                entry -> !entry.isDefault()).forEach(System.out::println);


        // Check if topic is compacted
        ConfigEntry compaction = new ConfigEntry(TopicConfig.CLEANUP_POLICY_CONFIG,
                TopicConfig.CLEANUP_POLICY_COMPACT);
        if (!configs.entries().contains(compaction)) {
            // if topic is not compacted, compact it
            Collection<AlterConfigOp> configOp = new ArrayList<>();
            configOp.add(new AlterConfigOp(compaction, AlterConfigOp.OpType.SET));
            Map<ConfigResource, Collection<AlterConfigOp>> alterConf = new HashMap<>();
            alterConf.put(configResource, configOp);
            admin.incrementalAlterConfigs(alterConf).all().get();
        } else {
            System.out.println("Topic " + TOPIC_NAME + " is compacted topic");
        }
        admin.close(Duration.ofSeconds(60));
    }
}
