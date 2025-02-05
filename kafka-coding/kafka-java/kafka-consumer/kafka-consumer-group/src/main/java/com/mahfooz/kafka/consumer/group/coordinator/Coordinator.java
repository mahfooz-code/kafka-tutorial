/*

The way consumers maintain membership in a consumer group and ownership of the partitions assigned to them is 
by sending heartbeats to a Kafka broker designated as the group coordinator (this broker can be different for 
different consumer groups).

As long as the consumer is sending heartbeats at regular intervals, it is assumed to be
alive, well, and processing messages from its partitions. 

Heartbeats are sent when the consumer polls (i.e., retrieves records) and when it commits records it has consumed.

If the consumer stops sending heartbeats for long enough, its session will time out and the group coordinator will consider it dead and trigger a rebalance.

If a consumer crashed and stopped processing messages, it will take the group coordinator a few seconds without heartbeats to decide it is dead and trigger the rebalance.

During
those seconds, no messages will be processed from the partitions owned by the dead
consumer. When closing a consumer cleanly, the consumer will notify the group
coordinator that it is leaving, and the group coordinator will trigger a rebalance
immediately, reducing the gap in processing.


 */
package com.mahfooz.kafka.consumer.group.coordinator;

public class Coordinator {
    public static void main(String[] args) {
        System.out.println();
    }
}
