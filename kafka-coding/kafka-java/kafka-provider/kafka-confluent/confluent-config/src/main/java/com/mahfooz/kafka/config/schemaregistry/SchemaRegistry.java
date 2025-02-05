/*
Navigate to the Schema Registry properties file (/etc/schema-registry/schema-registry.properties) and specify the following properties:

# Specify the address the socket server listens on, e.g. listeners = PLAINTEXT://your.host.name:9092
listeners=http://0.0.0.0:8081

# The host name advertised in ZooKeeper. This must be specified if your running Schema Registry
# with multiple nodes.
host.name=192.168.50.1

# List of Kafka brokers to connect to, e.g. PLAINTEXT://hostname:9092,SSL://hostname2:9092
kafkastore.bootstrap.servers=PLAINTEXT://hostname:9092,SSL://hostname2:9092

 */
package com.mahfooz.kafka.config.schemaregistry;

public class SchemaRegistry {
}
