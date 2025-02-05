/*
If acks=all, the producer will receive a success response from the broker once all in-sync replicas received the message.
This is the safest mode since you can make sure more than one broker has the message and that the message will survive
even in the case of crash.
However, the latency we discussed in the acks=1 case will be even higher, since we will be waiting for more than just one broker to receive the message.

 */
package com.mahfooz.kafka.producer.modes.acknowledge.all;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.commons.cli.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

public class AcknowledgementAll {

    private static Producer<Integer, String> producer;
<<<<<<< HEAD
    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";
    private  Properties props ;

    public AcknowledgementAll() throws IOException {
        props =ProducerConfigUtil.getProducerProps("");
        props.put( ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put( ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG,"all");
=======
    private final Properties props = new Properties();

    public AcknowledgementAll() {
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.ACKS_CONFIG, "all");
>>>>>>> 691e64dbe24618d3f48895ab1e33d5777267d869
        producer = new KafkaProducer<>(props);
    }

    public static void main(String[] args) throws ParseException, IOException {
        Options options = new Options();

        options.addOption(Option.builder("t").longOpt("topic").hasArg(true).desc("topic is ([REQUIRED] or use --topic)")
                .required(true).build());

        options.addOption(Option.builder("m").longOpt("message").hasArg(true)
                .desc("client id ([REQUIRED] or use --message)").required(true).build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);
        AcknowledgementAll sp = new AcknowledgementAll();
        String topic = null;
        String messageStr = null;
        if (cmd.hasOption("t")) {
            topic = cmd.getOptionValue("t");
        }

        if (cmd.hasOption("m")) {
            messageStr = cmd.getOptionValue("m");
        }

        ProducerRecord<Integer, String> data = new ProducerRecord<>(topic, messageStr);
        producer.send(data);
        producer.close();
    }
}
