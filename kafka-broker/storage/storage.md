# Storage

A Partition is the basic storage unit in Kafka.

A partition cannot be broken down and stored among multiple brokers or multiple discs of the same broker.

The administrator can provide a list of directories using the log.dirs parameter, to store partitions, during configuration.

This topic describes the following:

1. How are Partitions allocated among Brokers?
2. How is Retention period for messages handled?
3. Indexes
4. Log Compaction

## New partition assignment is based on the following

There should be minimum data movement.

The partitions of topics should be evenly distributed across brokers in the cluster.

No two partitions of a topic should remain in the same broker.

The replicas for each partition should be assigned to different racks.

For example, if brokers 0 and 1 are on the same rack and broker 2 and 3 are on another rack.

Then, the topic partitions will be stored on brokers in the order 0, 2, 1, 3 instead of 0 to 3. Each broker will be followed by a broker from a different rack.

Partition leaders should be evenly distributed across the cluster.
