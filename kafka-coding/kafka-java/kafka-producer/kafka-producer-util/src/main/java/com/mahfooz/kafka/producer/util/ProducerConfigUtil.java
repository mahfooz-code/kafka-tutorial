package com.mahfooz.kafka.producer.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProducerConfigUtil {

    public static Properties getProducerProps(String kafkaConfigFile) throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream(kafkaConfigFile));
        return props;
    }
}
