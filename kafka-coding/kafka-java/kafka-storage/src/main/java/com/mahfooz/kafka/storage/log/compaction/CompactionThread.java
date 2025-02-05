/*
Compaction Threads
When compaction is enabled, the following things happen:

The broker starts a compaction manager thread and multiple compaction threads. Compaction threads are responsible for the compaction task.

The compaction thread starts the compaction task by choosing the partition, with the highest ratio of dirty messages to the total size of the partition and starts cleaning.

The compaction thread reads the dirty portion of the partition and creates an in-memory heap of map entries. A map entry comprises of hash of each message (a key-value pair) of 16 bytes and the offset of the previous message that had the same key which is 8 bytes. Thus, each map entry is 24 bytes in size.

After building the offset map for the dirty segments of the partition log, the compaction thread starts reading the clean segments, starting from the oldest. It checks their content against the offset map.

*/
package com.mahfooz.kafka.storage.log.compaction;

public class CompactionThread {

}
