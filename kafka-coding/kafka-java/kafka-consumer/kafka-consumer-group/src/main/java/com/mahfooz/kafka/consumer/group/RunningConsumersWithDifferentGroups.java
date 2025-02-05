package com.mahfooz.kafka.consumer.group;

public class RunningConsumersWithDifferentGroups {
    public static void main(String[] args) throws Exception {
        String[] consumerGroups = new String[3];
        for (int i = 0; i < consumerGroups.length; i++) {
            consumerGroups[i] ="consumer-group-"+i;
        }
        ConsumerGroupExample.run(3, consumerGroups);
    }
}
