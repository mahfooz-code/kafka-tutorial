# kafka-tutorial

## How does Kafka simplify communication between systems?

Kafka simplifies communication between systems by acting as a centralized communication hub (often likened to a central nervous system), in which systems can send and receive data without knowledge of each other.

The communication pattern it implements is called the publish-subscribe pattern (or simply, pub/sub), and the result is a drastically simpler communication mode

## What are the main components in Kafka’s architecture?

## Which storage abstraction most closely models streams?

## How does Kafka store data in a fault-tolerant and durable manner?

## How is high availability and fault tolerance achieved at the data processing layers?

Logs are append-only data structures that capture an ordered sequence of events.

## How do the clients know where to send the requests?

Kafka clients use another request type called a metadata request, which includes a list of topics the client is interested in
The server response specifies which partitions exist in the topics, the replicas for each partition, and which replica is the leader.
Metadata requests can be sent to any broker because all brokers have a metadata cache that contains this information.

## Does the user sending the data have write privileges on the topic?

## Is the number of acks specified in the request valid (only 0, 1, and “all” are allowed)?
