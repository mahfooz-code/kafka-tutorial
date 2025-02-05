package com.mahfooz.kafka.config.option;

import org.apache.kafka.clients.consumer.ConsumerConfig;

public class ConsumerConfigParameter {

    public static void main(String[] args) {
        System.out.println(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG);
        System.out.println(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG);
    }
}
