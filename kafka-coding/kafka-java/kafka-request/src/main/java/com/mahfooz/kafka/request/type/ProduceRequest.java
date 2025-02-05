/*
Produce Requests
Produce requests are sent from the producer application and contain messages that need to be written to the Broker.

The Producer sends these requests only to the leader replica. Otherwise, it will receive an error response saying "Not a Leader for Partition".

The Producer could send these requests to the leader replica of a partition, by sending metadata request to any broker.


Produce Requests
On receiving a Produce request, the leader replica will check if the respective user has write privilege to the topic partition.

If the privilege is available, it will write the data and check 'ack' (acknowledgment configuration).

If it is 0, the leader replica will immediately acknowledge the Producer application.
If it is 1, then the leader replica acknowledges to the producer immediately after the request is written in the leader replica.
If it is set to all, the produce request is responded by the leader replica only after it receives acknowledgement from all the follower replicas. The request will be stored in a buffer called purgatory buffer till then.

*/
package com.mahfooz.kafka.request.type;

public class ProduceRequest {

}
