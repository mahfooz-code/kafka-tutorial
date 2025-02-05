package com.mahfooz.kafka.kafka.topic.metadata;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TopicMetadataDetails {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties properties = new Properties();
        properties.setProperty(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:9092");
        properties.setProperty(AdminClientConfig.CLIENT_ID_CONFIG, TopicMetadataDetails.class.getSimpleName());
        properties.setProperty(AdminClientConfig.METADATA_MAX_AGE_CONFIG, "3000");

        AdminClient admin = AdminClient.create(properties);

        Collection<ConfigResource> cr = Collections.singleton(
                new ConfigResource(ConfigResource.Type.TOPIC, "test_api"));

        DescribeConfigsResult ConfigsResult = admin.describeConfigs(cr);

        Config all_configs = (Config) ConfigsResult.all().get().values().toArray()[0];

        Iterator iterator = all_configs.entries().iterator();

        while (iterator.hasNext()) {
            ConfigEntry currentConfig = (ConfigEntry) iterator.next();
            System.out.println(currentConfig.name()+"="+currentConfig.value());
        }
    }
}
