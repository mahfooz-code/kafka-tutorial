package com.mahfooz.kafka.producer.partition.producer;

import com.mahfooz.kafka.producer.partition.CountryPartitioner;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Scanner;

public class PartitionProducer {

    private static Scanner in;

    public static void main(String[] argv) throws Exception {
        if (argv.length != 1) {
            System.err.println("Please specify 1 parameters ");
            System.exit(-1);
        }
        String topicName = argv[0];
        in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");

        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "mtmdevhdoped01:19092");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getCanonicalName());
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getCanonicalName());

        configProperties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CountryPartitioner.class.getCanonicalName());

        configProperties.put("partitions.0", "USA");
        configProperties.put("partitions.1", "India");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);
        String line = in.nextLine();
        while (!line.equals("exit")) {
            ProducerRecord<String, String> rec = new ProducerRecord<String, String>(topicName, line);
            producer.send(rec, new Callback() {
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    System.out.println("Message sent to topic ->" + metadata.topic() +
                            " ,parition->" + metadata.partition()
                            + " stored at offset->" + metadata.offset());
                }
            });
            line = in.nextLine();
        }
        in.close();
        producer.close();
    }
}
