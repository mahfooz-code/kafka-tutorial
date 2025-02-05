package com.mahfooz.spark.producer;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

public class KafkaProducerClient {

    private final Producer<String, String> producer;

    public KafkaProducerClient(Producer<String, String> producer) {
        this.producer = producer;
    }

    public Future<RecordMetadata> send(String key, String value) {
        ProducerRecord record = new ProducerRecord("topic_sports_news",
                key, value);
        return producer.send(record);
    }

    public void flush() {
        producer.flush();
    }

    public void beginTransaction() {
        producer.beginTransaction();
    }

    public void initTransaction() {
        producer.initTransactions();
    }

    public void commitTransaction() {
        producer.commitTransaction();
    }
}
