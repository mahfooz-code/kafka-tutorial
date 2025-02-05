/*

In an asynchronous replication, once the published message is written to the lead replica log, it acknowledges 
the message producer without waiting for an acknowledgment from follower replicas.

In such replication, in the event of broker failure, there is no guarantee that all the follower replicas would have 
committed the published message.

*/
package com.mahfooz.kafka.replication.asynchronus;

public class AsynchronousReplication {

}
