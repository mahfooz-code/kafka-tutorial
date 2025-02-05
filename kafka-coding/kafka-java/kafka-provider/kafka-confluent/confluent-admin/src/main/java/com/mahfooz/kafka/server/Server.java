/*


1)  Start ZooKeeper. Run this command in its own terminal.
    zookeeper-server-start <path-to-confluent>/etc/kafka/zookeeper.properties

2)  Start Kafka. Run this command in its own terminal.
    kafka-server-start <path-to-confluent>/etc/kafka/server.properties

3)  Start Schema Registry. Run this command in its own terminal.

    schema-registry-start <path-to-confluent>/etc/schema-registry/schema-registry.properties

4)  Start other Confluent Platform components as desired.

    Control Center

        control-center-start <path-to-confluent>/etc/confluent-control-center/control-center.properties

    Kafka Connect

        connect-distributed <path-to-confluent>/etc/schema-registry/connect-avro-distributed.properties

    Confluent REST Proxy

        kafka-rest-start <path-to-confluent>/etc/kafka-rest/kafka-rest.properties

    KSQL
        ksql-server-start <path-to-confluent>/etc/ksql/ksql-server.properties

 */
package com.mahfooz.kafka.server;

public class Server {
}
