/*
When a topic is created, Kafka decides how the partition should be allocated among brokers of the cluster.

New partition assignment is based on the following:

There should be minimum data movement.
The partitions of topics should be evenly distributed across brokers in the cluster.
No two partitions of a topic should remain in the same broker.
The replicas for each partition should be assigned to different racks. For example, if brokers 0 and 1 are on the same rack and broker 2 and 3 are on another rack. Then, the topic partitions will be stored on brokers in the order 0, 2, 1, 3 instead of 0 to 3. Each broker will be followed by a broker from a different rack.
Partition leaders should be evenly distributed across the cluster.


Partition Distribution
Given below is a high-level algorithm to distribute the partitions across the brokers evenly. Assuming the values for topic partitions, replication factor and the number of nodes are known.

Find out the number of partitions that each node can accept so that the number of partitions in each node is roughly the same.

Assign a partition from the current assignment back to their current owner nodes considering the number of partition each node can accept.

Assign all partition replicas that remain unassigned, evenly to nodes that can accept them.

*/
package com.mahfooz.kafka.storage.partition;

public class PartitionAllocation {

}
