package com.mahfooz.kafka.consumer.listener;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.common.TopicPartition;
import java.util.Collection;

public class HandleRebalance implements ConsumerRebalanceListener {

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {

    }
}
