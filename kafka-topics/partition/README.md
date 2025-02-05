# Partition

## Leader

Whenever a partition is replicated across multiple brokers, one broker will be designated as the leader, which means it will process all read/write requests from producers/consumers for the given partition.

## Follower

The other brokers that contain the replicated partitions are called followers, and they simply copy the data from the leader.

If the leader fails, then one of the followers will be promoted as the new leader.

## Load management

As the load on your cluster increases over time, you can expand your cluster by adding even more brokers, and triggering a partition reassignment.
