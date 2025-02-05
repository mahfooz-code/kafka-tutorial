/*

To turn on compaction for a topic, use topic config log.cleanup.policy=compact.

To set a delay to start compacting records after they are written, use topic config
log.cleaner.min.compaction.lag.ms .

Records won't get compacted until after this period.
The setting gives consumers time to get every record.

Log compaction retains at least the last known value for each record key for a single topic partition.
Compacted logs are useful for restoring state after a crash or system failure.
Log compaction is a granular retention mechanism that retains the last update for each key.
Only the active segment of a log can receive the newly produced messages.
 */
package com.mahfooz.kafka.admin.log.compaction;

public class LogCompaction {
}
