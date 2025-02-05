/*

The leader consumer is elected by the coordinator.
Its job is to assign partitions to every consumer in the group at startup or whenever a consumer leaves or joins the group.
The leader holds the assignment strategy, it is decoupled from the broker.
That means consumers can reconfigure the partition assignment strategy without restarting the brokers.

 */
package com.mahfooz.kafka.consumer.processing.leader;

public class LoaderConsumer {
}
