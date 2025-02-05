/*
Log Compaction
Kafka stores messages till it reaches its retention period and purges messages that are older than the retention period.

To store latest data, Kafka supports:

Retention Period of Topic: Messages that are older than the retention period will be deleted.

Compaction: This is done only on messages with key-value data inside the inactive partition segments. Compaction allows storing only the most recent value of each key in a message.

The partition log is split into two parts during compaction.

Clean: Portion of partition log that has messages already compacted. This will have messages with keys having only one value, which will be the latest value.

Dirty: Portion of the partition log that has messages written after the last compaction


Compaction - Key Validation
Compaction - Key Validation
It checks if the key of the message is present in the offset map.

If the key is present, the message in the segment is ignored, since there will be a newer value for the key later in the partition.

If the key is not present, the corresponding message will be copied to a replacement segment, since it is the newest message for that key.

Once the replacement segment is built with all latest messages for their keys, the original segment is replaced.

Finally, only clean segments exist with one message per key – each keys having the latest value.


Log compaction ensures that Kafka will always retain at least the last known value for each message key within the log
of data for a single topic partition.
It addresses use cases and scenarios such as restoring state after application crashes or system failure, or reloading
caches after application restarts during operational maintenance.
Log compaction is a mechanism to give finer-grained per-record retention, rather than the coarser-grained time-based retention

min.compaction.lag.ms
    can be used to guarantee the minimum length of time must pass after a message is written before it could be compacted.
    It provides a lower bound on how long each message will remain in the (un-compacted) head.

max.compaction.lag.ms
    It can be used to guarantee the maximum delay between the time a message is written and the time the message becomes eligible
    for compaction.

delete.retention.ms

log.cleanup.policy=compact

 */
package com.mahfooz.kafka.storage.log.compaction;

public class LogCompaction {
}
