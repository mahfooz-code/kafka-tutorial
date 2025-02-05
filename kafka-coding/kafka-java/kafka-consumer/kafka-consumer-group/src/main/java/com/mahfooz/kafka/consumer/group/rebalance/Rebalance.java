/*

Moving partition ownership from one consumer to another is called a rebalance.
Transferring the partitions of one consumer, to another consumer is called a rebalance.
Rebalance makes the consumer group highly available and scalable.
During a consumer rebalance, consumers cannot consume messages or change their current state.
Consumers ensure their membership in a consumer group by sending heartbeats to a broker that is declared as
the group coordinator for the consumer group and will be different for each consumer group.
Heartbeats are sent when the consumer is retrieving messages from its topic partitions.
As long as a consumer sends heartbeats at regular intervals to the broker, it is considered to be live, and
processing messages from the partitions it is assigned with.
If the consumer stops sending heartbeats for a long time, the group coordinator will consider the consumer is dead
and triggers a consumer rebalance.
When the consumer is crashed, it makes the group coordinator wait for some time without heartbeats, and decide that
it is dead and a rebalance should be triggered.
When the consumer is completely closed, it notifies the coordinator that it is going to leave the group, and the
group coordinator will immediately trigger a rebalance.
A consumer that needs to join a group sends JoinGroup request to the group coordinator.
The first consumer to join the group becomes the leader of consumers in the consumer group.
The leader gets a list of all consumers in the group from the group coordinator.
The leader consumer assigns unique partitions to each consumer in the consumer group.
The leader uses PartitionAssigner implementation for this purpose.
Once deciding the partition com.mahfooz.kafka.partition.assignment, the consumer leader sends a list of partition assignments to group coordinator.
Group coordinator sends the com.mahfooz.kafka.partition.assignment information to corresponding consumers.
The consumer leader will have the complete list of consumers in the group and the partitions assigned to them.
*/
package com.mahfooz.kafka.consumer.group.rebalance;

public class Rebalance {
    public static void main(String[] args) {
        System.out.println();
    }
}
