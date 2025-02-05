/*
Kafka stores messages till it reaches its retention period and purges messages that are older than the retention period.
To store latest data, Kafka supports:

    Retention Period of Topic:
        Messages that are older than the retention period will be deleted.

    Compaction:
        This is done only on messages with key-value data inside the inactive partition segments.
        Compaction allows storing only the most recent value of each key in a message.

Compaction Threads
When compaction is enabled, the following things happen:

The broker starts a compaction manager thread and multiple compaction threads. Compaction threads are responsible for the compaction task.

The compaction thread starts the compaction task by choosing the partition, with the highest ratio of dirty messages to the total size of the partition and starts cleaning.

The compaction thread reads the dirty portion of the partition and creates an in-memory heap of map entries. A map entry comprises of hash of each message (a key-value pair) of 16 bytes and the offset of the previous message that had the same key which is 8 bytes. Thus, each map entry is 24 bytes in size.

After building the offset map for the dirty segments of the partition log, the compaction thread starts reading the clean segments, starting from the oldest. It checks their content against the offset

 */
package com.mahfooz.kafka.kafka.topic.compaction;

public class LogCompaction {
}
