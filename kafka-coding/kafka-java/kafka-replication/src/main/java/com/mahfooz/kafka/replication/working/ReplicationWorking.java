/*
How Replication Works?

1)  Fetch requests are constantly sent by the follower replicas to the leader to consume recent messages. 
    The request contains an offset of the message the replica wants to receive.
    The leader responds to the request with a message at that offset.

2)  Message requests to the leader are in sequential order. 
    The offset requested by the replica, helps the leader to identify the last message received by the follower replica.

3)  A replica is considered out of sync when it fails to update with recent messages within 10 seconds and loses 
    credibility to become a leader in the event of some failures with the current leader in the cluster.

*/
package com.mahfooz.kafka.replication.working;

public class ReplicationWorking {

}
