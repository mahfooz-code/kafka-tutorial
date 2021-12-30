#   Consumer Groups
    Kafka is optimized for high throughput and low latency.
    To take advantage of this on the consumer side, we need to be able to parallelize work across multiple 
    processes.
    This is accomplished with consumer groups.

    Consumer groups are made up of multiple cooperating consumers, and the membership of these groups can change over time.

#   Group coordinator
    Every consumer group is assigned to a special broker called the group coordinator, which is responsible for 
    receiving heartbeats from the consumers, and triggering a rebalance of work whenever a consumer is marked as dead.
    Every active member of the consumer group is eligible to receive a partition assignment.

    