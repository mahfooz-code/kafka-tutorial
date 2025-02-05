/*

If acks=1, the producer will receive a success response from the broker the moment the leader replica received the message. If the message can’t be written
to the leader (e.g., if the leader crashed and a new leader was not elected yet), the producer will receive an error response and can retry sending the message,
avoiding potential loss of data. The message can still get lost if the leader crashes and a replica without this message gets elected as the new leader (via unclean
leader election). In this case, throughput depends on whether we send messages synchronously or asynchronously.

If our client code waits for a reply from the server (by calling the get() method of the Future object returned when
sending a message) it will obviously increase latency significantly (at least by a network roundtrip).

If the client uses callbacks, latency will be hidden, but throughput will be limited by the number of in-flight messages (i.e., how many messages the producer will send before receiving replies from the server).

 */
package com.mahfooz.kafka.producer.modes.acknowledge.one;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class AcknowledgeOne {
    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "10.133.43.96:9092,10.133.43.97:9092,10.133.43.95:9092");

        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");


        System.out.println();

    }
}
