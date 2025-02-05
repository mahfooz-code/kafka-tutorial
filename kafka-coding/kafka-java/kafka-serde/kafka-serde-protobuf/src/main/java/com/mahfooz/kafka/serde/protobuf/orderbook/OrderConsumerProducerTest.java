/*
java \
    -classpath /app/cloudera/opt/cloudera/parcels/CDH/lib/kafka/libs/*:kafka-serde-protobuf-jar-with-dependencies.jar \
    com.mahfooz.kafka.serde.protobuf.orderbook.OrderConsumerProducerTest
 */
package com.mahfooz.kafka.serde.protobuf.orderbook;

public class OrderConsumerProducerTest {

    public static void main(String[] args) {
        OrderProducer producerThread = new OrderProducer("protobuf-topic");
        producerThread.start();

        OrderConsumer consumerThread = new OrderConsumer("protobuf-topic");
        consumerThread.start();

    }
}