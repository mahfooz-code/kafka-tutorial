/*

The RangeAssignor is the default strategy.
The aims of this strategy is to co-localized partitions of several topics.
This is useful, for example, to join records from two topics which have the same number of partitions and the same key-partitioning
logic.

For doing this, the strategy will first put all consumers in lexicographic order using the member_id assigned by the broker coordinator.
Then, it will put available topic-partitions in numeric order. Finally, for each topic, the partitions are assigned starting from the first consumer .
As you can seen, partitions 0 from topics A and B are assigned to the same consumer.
In the example, at most two consumers are used because we have maximum of two partitions per topic . If you plan to consume from multiple input topics and you are not performing an operation requiring to co-localized partitions you should definitely not use the default strategy.

 */
package com.mahfooz.kafka.consumer.assigment.strategy.range;

public class RangeAssigner {
}
