# Request type

There are three types of requests managed by the broker. They are:

## Produce

        Produce requests are sent from the producer application and contain messages that need to be written to the Broker.
        The Producer sends these requests only to the leader replica. Otherwise, it will receive an error response saying
        "Not a Leader for Partition".
        The Producer could send these requests to the leader replica of a partition, by sending metadata request to any broker.

        On receiving a Produce request, the leader replica will check if the respective user has write privilege to the topic partition.

        If the privilege is available, it will write the data and check 'ack' (acknowledgment configuration).

        If it is 0, the leader replica will immediately acknowledge the Producer application.
        If it is 1, then the leader replica acknowledges to the producer immediately after the request is written in the leader replica.
        If it is set to all, the produce request is responded by the leader replica only after it receives acknowledgement from all the follower replicas. The request will be stored in a buffer called purgatory buffer till then.

## Metadata

        Metadata Requests are sent both by the Producer and Consumer to any broker and contain a list of topics it is
        interested in.
        The broker will respond to metadata request with the partitions of the topic, replicas for the partition, and the
        leader replica.
        It could be sent to any broker since all brokers have a copy of metadata cache with this information.
        It needs to be refreshed intermittently by sending new ones, to know if the topic metadata changed or if the client
        received Not a Leader for Partition error.

## Fetch

Fetch requests are sent by the consumer to Broker to read messages from an offset of a topic's leader partition.

It is also used among Brokers to replicate partitions.

The client sends an upper limit (maximum amount of data the client can accept from the broker), and a lower limit (minimum amount of data needed for a data transfer to happen).

The Fetch request should have an upper limit.

Otherwise, the broker will send a large amount of data making the client run out of memory.

Likewise, setting a minimum limit will ensure that broker sends the response only when there is sizable amount of data available to be sent to the client.

Else, smaller amount of data processing happens resulting in resources being utilized inefficiently.

As soon as a leader replica receives a Fetch request, it validates the request by checking if the offset specified in the request exists for that particular partition.

If it is valid, the leader replica will read messages from that offset till the specified upper limit and return the messages to the client.

The broker will respond with an error message if it is an old one or if it does not exist.

To avoid any inconsistency, the leader replica always sends messages that are replicated to all its followers.
