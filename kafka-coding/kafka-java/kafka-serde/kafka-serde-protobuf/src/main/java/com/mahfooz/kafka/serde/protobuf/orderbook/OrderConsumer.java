package com.mahfooz.kafka.serde.protobuf.orderbook;

import kafka.utils.ShutdownableThread;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import kafka.message.ExchangeMessage.Order;
import com.mahfooz.kafka.serde.protobuf.deserializer.OrderDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.common.serialization.IntegerDeserializer;

public class OrderConsumer extends ShutdownableThread {

    private final KafkaConsumer<Integer, Order> consumer;
    private final String topic;

    public OrderConsumer(String topic) {
        super("KafkaConsumerExample", false);
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:19092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "OrderConsumer");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");

        consumer = new KafkaConsumer<>(props, new IntegerDeserializer(), new OrderDeserializer());
        this.topic = topic;
    }

    @Override
    public void doWork() {
        consumer.subscribe(Collections.singletonList(this.topic));
        ConsumerRecords<Integer, Order> records = consumer.poll(Duration.ofSeconds(10));
        for (ConsumerRecord<Integer, Order> record : records) {
            System.out.println("Received message: (" + record.key() + ", " + record.value() + ") at offset " + record.offset());
        }
    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public boolean isInterruptible() {
        return false;
    }
}