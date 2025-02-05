# Group co-ordinator

The way consumers maintain membership in a consumer group and ownership of the partitions assigned to them is by sending heartbeats to a Kafka broker designated as the group coordinator.

As long as the consumer is sending heartbeats at regular intervals, it is assumed to be alive, well, and processing messages from its partitions.

Heartbeats are sent when the consumer polls (i.e., retrieves records) and when it commits records it has consumed.
