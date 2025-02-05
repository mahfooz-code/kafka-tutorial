package com.mahfooz.kafka.serde.protobuf.orderbook;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import kafka.message.ExchangeMessage.Order;
import com.mahfooz.kafka.serde.protobuf.serializer.OrderSerializer;

import org.apache.kafka.common.serialization.IntegerSerializer;

public class OrderProducer extends Thread {

    private final KafkaProducer<Integer, Order> producer;
    private final String topic;

    public OrderProducer(String topic) {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:19092");
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "OrderProducer");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,IntegerSerializer.class.getCanonicalName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,OrderSerializer.class.getCanonicalName());
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void run() {
        int messageNo = 1;
        while (true) {
            Order order = Order.newBuilder().setOid("xxx" + String.valueOf(messageNo)).build();
            try {
                producer.send(new ProducerRecord<>(topic,
                        messageNo,
                        order)).get();
                System.out.println("Sent message: (" + messageNo + ", " + order.toString() + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ++messageNo;
        }
    }
}