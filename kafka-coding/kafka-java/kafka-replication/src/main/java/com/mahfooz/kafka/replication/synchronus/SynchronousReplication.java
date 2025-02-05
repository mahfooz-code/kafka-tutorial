/*
Synchronous replication works as follows:

1)  The Producer identifies the lead replica from the Zookeeper and publishes a message.

2)  The published message is written to the log of the lead replica, and all of its followers in ISR (In-sync Replica) 
    start to pull the messages. 
    Please note that every lead replica maintains In-Sync Replicas (ISR) in the cluster. 
    These are the minimum set of replicas that ought to be in sync with the lead replica whenever there is a change 
    in the lead replica.

3)  Once the message is written to its respective logs, each of the follower replicas send an acknowledgment to the 
    lead replica.

4)  Once all expected acknowledgments are received, and the replication is complete, the lead replica sends an 
    acknowledgment to the Producer.
    The consumer pulls the message from the lead replica.

*/
package com.mahfooz.kafka.replication.synchronus;

public class SynchronousReplication {

}
