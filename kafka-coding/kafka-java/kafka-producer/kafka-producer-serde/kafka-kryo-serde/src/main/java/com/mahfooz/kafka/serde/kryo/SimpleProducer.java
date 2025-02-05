package com.mahfooz.kafka.serde.kryo;

import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;
import java.util.concurrent.Future;

public class SimpleProducer<K extends Serializable, V extends Serializable> {

    private final Logger logger = LoggerFactory.getLogger(SimpleProducer.class);

    private KafkaProducer<byte[], byte[]> producer;
    private boolean syncSend;
    private volatile boolean shutDown = false;


    public SimpleProducer(Properties producerConfig) {
        this(producerConfig, true);
    }

    public SimpleProducer(Properties producerConfig, boolean syncSend) {
        this.syncSend = syncSend;
        this.producer = new KafkaProducer<>(producerConfig);
        logger.info("Started Producer.  sync  : {}", syncSend);
    }

    public void send(String topic, V v) {
        send(topic, -1, null, v, new DummyCallback());
    }

    public void send(String topic, K k, V v) {
        send(topic, -1, k, v, new DummyCallback());
    }

    public void send(String topic, int partition, V v) {
        send(topic, partition, null, v, new DummyCallback());
    }

    public void send(String topic, int partition, K k, V v) {
        send(topic, partition, k, v, new DummyCallback());
    }

    public void send(String topic, int partition, K key, V value, Callback callback) {
        if (shutDown) {
            throw new RuntimeException("Producer is closed.");
        }

        try {
            ProducerRecord record;
            if(partition < 0)
                record = new ProducerRecord<>(topic, key, value);
            else
                record = new ProducerRecord<>(topic, partition, key, value);


            Future<RecordMetadata> future = producer.send(record, callback);
            if (!syncSend) return;
            future.get();
        } catch (Exception e) {
            logger.error("Error while producing event for topic : {}", topic, e);
        }

    }


    public void close() {
        shutDown = true;
        try {
            producer.close();
        } catch (Exception e) {
            logger.error("Exception occurred while stopping the producer", e);
        }
    }

    private class DummyCallback implements Callback {
        @Override
        public void onCompletion(RecordMetadata recordMetadata, Exception e) {
            if (e != null) {
                logger.error("Error while producing message to topic : {}", recordMetadata.topic(), e);
            } else
                logger.debug("sent message to topic:{} partition:{}  offset:{}", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset());
        }
    }
}