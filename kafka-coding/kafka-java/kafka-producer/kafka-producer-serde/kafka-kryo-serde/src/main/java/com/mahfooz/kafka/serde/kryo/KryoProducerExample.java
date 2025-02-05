package com.mahfooz.kafka.serde.kryo;
import com.mahfooz.kafka.serde.kryo.message.MyEvent;
import net.sourceforge.argparse4j.ArgumentParsers;
import net.sourceforge.argparse4j.inf.ArgumentParser;
import net.sourceforge.argparse4j.inf.ArgumentParserException;
import net.sourceforge.argparse4j.inf.Namespace;
import org.apache.kafka.clients.producer.ProducerConfig;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Properties;

import static net.sourceforge.argparse4j.impl.Arguments.store;

public class KryoProducerExample {

    public static void main(String[] args) {
        ArgumentParser parser = argParser();

        try {
            Namespace res = parser.parseArgs(args);

            /* parse args */
            String brokerList = res.getString("bootstrap.servers");
            String topic = res.getString("topic");
            Boolean syncSend = res.getBoolean("syncsend");
            long noOfMessages = res.getLong("messages");
            long delay = res.getLong("delay");
            String messageType = res.getString("messagetype");


            Properties producerConfig = new Properties();
            producerConfig.put("bootstrap.servers", brokerList);
            producerConfig.put("client.id", "basic-producer");
            producerConfig.put("acks", "all");
            producerConfig.put("retries", "3");
            producerConfig.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "kafka.examples.kryo.serde.KryoSerializer");
            producerConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "kafka.examples.kryo.serde.KryoSerializer");

            SimpleProducer<String, Serializable> producer = new SimpleProducer<>(producerConfig, syncSend);

            for (int i = 0; i < noOfMessages; i++) {
                producer.send(topic, new Integer(i), getEvent(messageType, i));
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            producer.close();
        } catch (ArgumentParserException e) {
            if (args.length == 0) {
                parser.printHelp();
                System.exit(0);
            } else {
                parser.handleError(e);
                System.exit(1);
            }
        }

    }

    private static Serializable getEvent(String messageType, int i) {
        if ("string".equalsIgnoreCase(messageType))
            return "message" + i;
        else
            return new MyEvent(i, "event" + i, "test", System.currentTimeMillis());
    }


    /**
     * Get the command-line argument parser.
     */
    private static ArgumentParser argParser() {
        ArgumentParser parser = ArgumentParsers
                .newArgumentParser("simple-producer")
                .defaultHelp(true)
                .description("This example is to demonstrate kafka producer capabilities");

        parser.addArgument("--bootstrap.servers").action(store())
                .required(true)
                .type(String.class)
                .metavar("BROKER-LIST")
                .help("comma separated broker list");

        parser.addArgument("--topic").action(store())
                .required(true)
                .type(String.class)
                .metavar("TOPIC")
                .help("produce messages to this topic");

        parser.addArgument("--messages").action(store())
                .required(true)
                .type(Long.class)
                .metavar("NUM-MESSAGE")
                .help("number of messages to produce");

        parser.addArgument("--syncsend").action(store())
                .required(false)
                .type(Boolean.class)
                .setDefault(true)
                .metavar("TRUE/FALSE")
                .help("sync/async send of messages");

        parser.addArgument("--delay").action(store())
                .required(false)
                .setDefault(10l)
                .type(Long.class)
                .metavar("DELAY")
                .help("number of milli seconds delay between messages.");

        parser.addArgument("--messagetype").action(store())
                .required(false)
                .setDefault("string")
                .type(String.class)
                .choices(Arrays.asList("string", "myevent"))
                .metavar("STRING/MYEVENT")
                .help("generate string messages or MyEvent messages");

        return parser;
    }

}