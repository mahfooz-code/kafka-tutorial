/*
Kafka supports two forms of replication :

    a)  Synchronous Replication
        Synchronous replication works as follows:

        a)  The Producer identifies the lead replica from the Zookeeper and publishes a message.

        b)  The published message is written to the log of the lead replica, and all of its followers in ISR (In-sync Replica)
            start to pull the messages.
            Please note that every lead replica maintains In-Sync Replicas (ISR) in the cluster.
            These are the minimum set of replicas that ought to be in sync with the lead replica whenever there is a change in
            the lead replica.

        c)  Once the message is written to its respective logs, each of the follower replicas send an acknowledgment to the
            lead replica.

        d)  Once all expected acknowledgments are received, and the replication is complete, the lead replica sends an
            acknowledgment to the Producer.

        e)  The consumer pulls the message from the lead replica.

    b)  Asynchronous Replication

        In an asynchronous replication, once the published message is written to the lead replica log, it acknowledges the
        message producer without waiting for an acknowledgment from follower replicas.
        In such replication, in the event of broker failure, there is no guarantee that all the follower replicas would have
        committed the published message.
. Choosing the correct number of partitions and partition replications for a topic

Spreads leader partitions evenly on brokers throughout the cluster
Makes partitions within the same topic are roughly the same size.
Balances the load on brokers.

 */
package com.mahfooz.kafka.kafka.topic.data.replication;

public class DataReplication {
}
