package com.mahfooz.kafka.consumer.group;

import java.util.Arrays;

public class Running2Consumers {
    public static void main(String[] args) throws Exception {
        String[] consumerGroups = new String[2];
        Arrays.fill(consumerGroups, "consumer-group");
        ConsumerGroupExample.run(2, consumerGroups);
    }
}
