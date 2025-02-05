package com.mahfooz.kafka.consumer.group;

import java.util.Arrays;

public class Running4Consumers {
    public static void main(String[] args) throws Exception {
        String[] consumerGroups = new String[4];
        Arrays.fill(consumerGroups, "consumer-group");
        ConsumerGroupExample.run(4, consumerGroups);
    }
}
