/*

When a consumer wants to join a group, it sends a JoinGroup request to the group coordinator.

The first consumer to join the group becomes the group leader.

Static membership aims to improve the availability of stream applications, consumer groups and other applications
built on top of the group rebalance protocol.

The rebalance protocol relies on the group coordinator to allocate entity ids to group members.
These generated ids are ephemeral and will change when members restart and rejoin.

 */
package com.mahfooz.kafka.consumer.group.membership;

public class Membership {
    public static void main(String[] args) {
        System.out.println();
    }
}
