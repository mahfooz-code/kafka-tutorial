/*
KafkaConsumer manages some state by periodically committing the offsets of messages consumed from Kafka.
Offsets uniquely identify messages and represent the starting positions of messages in the log.
Consumers periodically need to commit the offsets of messages they have received.

Committing an offset has two implications for a consumer:

    a) Committing implies the consumer has fully processed the message.
    b) Committing also represents the starting point for that consumer in the case of failure or a restart.

If you have a new consumer instance or some failure has occurred, and the last committed offset isn’t available,
where the consumer starts from will depend on your configuration:

    a)  auto.offset.reset="earliest"
        Messages will be retrieved starting at the earliest available offset.
        Any messages that haven’t yet been removed by the log management process will be retrieved.

    b)  auto.offset.reset="latest"
        Messages will be retrieved from the latest offset, essentially only consuming messages from the point of joining
        the cluster.

    c)  auto.offset.reset="none"
        No reset strategy is specified.
        The broker throws an exception to the consumer.

 */
package com.mahfooz.kafka.consumer.offset;

public class KafkaConsumerOffset {
}
