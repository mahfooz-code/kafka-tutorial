/*

What is partition rebalance?
Within a consumer group, Kafka changes the ownership of partition from one consumer to another at certain events.

The process of changing partition ownership across the consumers is called a rebalance.

Rebalance happens at following events:

(1) A new consumer joins a consumer group
    A partition rebalance is triggered when we add a new consumer to the group.
    The new consumer starts consuming messages from partitions previously consumed by another consumer.

(2) An existing consumer shuts down
    A partition rebalance is triggered when a consumer shuts down or crashes.
    The partitions it used to consume will be consumed by one of the remaining consumers.

(3) The topic is modified
    A partition rebalance is also triggered when the topics the consumer group is consuming are modified.
    For example new partitions are added to the topic.

Why partition rebalance needed?
Rebalances are important because they provide the consumer group with high availability and scalability (allowing us to easily and
safely add and remove consumers)

When partition rebalance is not applicable?
Partition rebalance does not happen when consumer are manually assigned the partitions.

The consumer config rebalance.max.retries
If the set of consumers changes while rebalancing is taking place, the rebalancing will fail and retry.
The setting rebalance.max.retries controls the maximum number of attempts before giving up. The default value is 4.

The consumer config rebalance.backoff.ms
This setting rebalance.backoff.ms is the backoff time in milliseconds between retries during rebalance.
The default is 2000 ms.

 */
package com.mahfooz.kafka.partition.assignment.rebalance;

public class PartitionRebalance {

}
