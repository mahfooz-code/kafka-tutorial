/*

In Kafka, brokers maintain their membership in the cluster through Zookeeper.
Every broker in a cluster has a unique ID specified in their configuration file.
As soon as a broker process starts, it registers itself with the Zookeeper with its unique ID creating an ephemeral node.
Kafka Components such as producer and consumer subscribe to the /broker/ids path in Zookeeper and get notified each time a
broker is added or removed in the cluster

 */
package com.mahfooz.kafka.cluster.membership;

public class Membership {
}
