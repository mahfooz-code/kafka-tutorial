/*

1)  Start the ZooKeeper server.

    bin/zookeeper-server-start.sh config/zookeeper.properties

2)  Starting the kafka broker.

    bin/kafka-server-start.sh config/server.properties

3)  Starting a Kafka Producer
    
    bin/kafka-console-producer.sh --broker-list localhost:9092 --topic kafka

4)  Starting a Kafka Consumer
    
    bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic kafka --from-beginning

 */

package com.mahfooz.kafka.admin.server;

/**
 *
 * @author Mahfooz Alam
 */
public class Start {
    public static void main(String[] args) {
        System.out.println();
    }
}
