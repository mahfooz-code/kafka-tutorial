# ISR

Brokers that follow a topic partition consume messages from the topic-partition leader and append those records to their log.

The follower brokers that are caught up with their leader broker are considered to be ISRs.

ISR brokers are eligible to be elected leader, should the current leader fail or become unavailable.
