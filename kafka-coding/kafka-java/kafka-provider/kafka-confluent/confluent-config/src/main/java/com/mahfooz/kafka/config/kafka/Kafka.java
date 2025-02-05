/*
Navigate to the Apache Kafka® properties file (/etc/kafka/server.properties) and customize the following:

Connect to the same ZooKeeper ensemble by setting the zookeeper.connect in all nodes to the same value. Replace all instances of localhost to the hostname or FQDN (fully qualified domain name) of your node. For example, if your hostname is zookeeper:

zookeeper.connect=zookeeper:2181
Configure the broker IDs for each node in your cluster using one of these methods.

Dynamically generate the broker IDs: add broker.id.generation.enable=true and comment out broker.id. For example:

############################# Server Basics #############################

# The ID of the broker. This must be set to a unique integer for each broker.
#broker.id=0
broker.id.generation.enable=true
Manually set the broker IDs: set a unique value for broker.id on each node.

Configure how other brokers and clients communicate with the broker using listeners, and optionally advertised.listeners.

listeners: Comma-separated list of URIs and listener names to listen on.
advertised.listeners: Comma-separated list of URIs and listener names for other brokers and clients to use. The advertised.listeners parameter ensures that the broker advertises an address that is accessible from both local and external hosts.
For more information, see Production Configuration Options.

Configure security for your environment.

For general security guidance, see Security.
For role-based access control (RBAC), see Configure Metadata Service (MDS).
For SSL encryption, SASL authentication, and authorization, see Security Tutorial.

 */
package com.mahfooz.kafka.config.kafka;

public class Kafka {
}
