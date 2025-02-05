/*

The first consumer to join the group becomes the group leader.

The leader receives a list of all consumers in the group from the group coordinator (this will include all consumers that sent a heartbeat recently and which are therefore considered alive) and is responsible for assigning a subset of partitions to each consumer.

It uses an implementation of PartitionAssignor to decide which partitions should be handled by which consumer.

After deciding on the partition com.mahfooz.kafka.partition.assignment, the consumer leader sends the list of assignments to the GroupCoordinator, which sends this information to all the consumers.

Each consumer only sees his own com.mahfooz.kafka.partition.assignment the leader is the only client process that has the full list of consumers in the group and their assignments.

This process repeats every time a rebalance happens.

*/
package com.mahfooz.kafka.consumer.group.leader;

public class GroupLeader {
    public static void main(String[] args) {
        System.out.println();
    }
}
