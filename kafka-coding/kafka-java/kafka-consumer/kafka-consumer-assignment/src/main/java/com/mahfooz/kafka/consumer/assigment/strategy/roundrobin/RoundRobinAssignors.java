/*

The RoundRobinAssignor can be used to distribute available partitions evenly across all members.
As previously, the assignor will put partitions and consumers in lexicographic order before assigning each partitions.
Even if RoundRobin provides the advantage of maximizing the number of consumers used, it has one major drawback.
Indeed, it does not attempt to reduce partition movements when the number of consumers changes (i.e. when a rebalance occurs).
For example, if a consumer initializes internal caches, opens resources or connections during partition assignment, this unnecessary partition movement can have an impact on consumer performance.

 */
package com.mahfooz.kafka.consumer.assigment.strategy.roundrobin;

public class RoundRobinAssignors {
}
