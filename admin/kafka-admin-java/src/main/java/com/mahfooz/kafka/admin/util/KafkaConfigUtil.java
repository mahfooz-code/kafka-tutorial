package com.mahfooz.kafka.admin.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class KafkaConfigUtil {

    public static Properties kafkaProperties(String kafkaConfigFile) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(kafkaConfigFile));
        return props;
    }
}
