# Broker

A single Kafka server is called a broker.

The broker receives messages from producers, assigns offsets to them, and commits the messages to storage on disk.

It also services consumers, responding to fetch requests for partitions and responding with the messages that have
been committed to disk.

Kafka is designed to run on multiple hosts, with one broker per host.

The broker, topic, and partition information are maintained in Zookeeper.

Kafka Brokers also store other relevant metadata in Zookeeper
