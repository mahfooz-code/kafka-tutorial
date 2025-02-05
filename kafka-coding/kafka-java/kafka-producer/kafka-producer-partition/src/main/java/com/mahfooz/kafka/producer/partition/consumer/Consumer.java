package com.mahfooz.kafka.producer.partition.consumer;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.Scanner;

public class Consumer {

    private static Scanner in;
    private static boolean stop = false;

    public static void main(String[] argv) throws Exception {
        if (argv.length != 2) {
            System.err.printf("Usage: %s <topicName> <groupId>\n",
                    Consumer.class.getSimpleName());
            System.exit(-1);
        }
        in = new Scanner(System.in);
        String topicName = argv[0];
        String groupId = argv[1];
        ConsumerThread consumerThread = new ConsumerThread(topicName, groupId);
        consumerThread.start();
        String line = "";
        while (!line.equals("exit")) {
            line = in.next();
        }
        consumerThread.getKafkaConsumer().wakeup();
        System.out.println("Stopping consumer .....");
        consumerThread.join();
    }

    private static class ConsumerThread extends Thread {
        private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

        private String topicName;
        private String groupId;
        private KafkaConsumer<String, String> kafkaConsumer;

        public ConsumerThread(String topicName, String groupId) {
            this.topicName = topicName;
            this.groupId = groupId;
        }

        @Override
        public void run() {
            Properties configProperties = null;
            try {
                configProperties = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
            configProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
            configProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getCanonicalName());
            configProperties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);

            //Figure out where to start processing messages from
            kafkaConsumer = new KafkaConsumer<String, String>(configProperties);
            kafkaConsumer.subscribe(Arrays.asList(topicName), new ConsumerRebalanceListener() {
                public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                    System.out.printf("%s topic-partitions are revoked from this consumer\n",
                            Arrays.toString(partitions.toArray()));
                }
                public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                    System.out.printf("%s topic-partitions are assigned to this consumer\n",
                            Arrays.toString(partitions.toArray()));
                }
            });
            //Start processing messages
            try {
                while (true) {
                    ConsumerRecords<String, String> records = kafkaConsumer.poll(100);
                    for (ConsumerRecord<String, String> record : records)
                        System.out.println(record.value());
                }
            } catch (WakeupException ex) {
                System.out.println("Exception caught " + ex.getMessage());
            } finally {
                kafkaConsumer.close();
                System.out.println("After closing KafkaConsumer");
            }
        }
        public KafkaConsumer<String, String> getKafkaConsumer() {
            return this.kafkaConsumer;
        }
    }
}