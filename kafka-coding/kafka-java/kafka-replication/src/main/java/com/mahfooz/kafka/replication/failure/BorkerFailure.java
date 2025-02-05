/*
Handling Broker Failures

1)  As soon as a follower in the ISR (in-sync replica) fails, the leader removes it from the ISR and continues 
    writing to other followers in the ISR.

2)  Once it comes back, it will immediately truncate its log to the offset position of the last message committed to 
    its log.

3)  Then it starts reading the messages from that point from the leader.

4)  Once it is fully synced with the leader, it is added back to the current ISR by the leader.


Handling Leader Failures

1)  When a leader fails while writing a message to its log or acknowledging the producer, a new leader is elected.
2)  To elect the new leader, all the follower ISRs register themselves with the Zookeeper.
3)  The first one to register becomes the new leader replica, and the offset of its log end becomes the offset of the 
    last committed message.
4)  All other replicas become the follower of the new leader.
5)  Each replica registers a listener in the Zookeeper so that it gets notified of any leader change.
6)  Once there is a leader change, the replica truncates to the offset of the last committed message and starts to 
    catch up with the new leader.
7)  The new leader waits until all live replicas get in sync, write the new ISR to the Zookeeper and opens itself for 
    any read/write from producer or consumer.
*/
package com.mahfooz.kafka.replication.failure;

public class BorkerFailure {

}
