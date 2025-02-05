/*

Partition Segments
Each partition is split into segments of a particular size, say 1 GB or configured to be retained for a proper amount of time, say one week.

A message from the Producer is written to multiple partitions.

Each partition has numerous segments. When any of the above limits (size/retention period) exceeds while writing data to a partition segment, then that segment is closed, and a new one is started.

A segment to which Broker is currently writing is called an active segment.

An active segment will never be deleted until any of the segment's limits are exceeded.

A broker may open multiple file handles for every segment for all of its partitions, irrespective of active or inactive.

The log-deletion strategy is a two-phased approach: first, the logs are rolled into segments, and then the oldest segments are
deleted.
To manage the increasing size of the logs, Kafka rolls them into segments.
The timing of log rolling is based on timestamps embedded in the messages.
Kafka rolls a log when a new message arrives, and its timestamp is greater than the timestamp of the first message in the log
plus the log.roll.ms configuration value.

There are two options for log rolling:

log.roll.ms
    This is the primary configuration, but there’s no default value.

log.roll.hours
    This is the secondary configuration, which is only used if

log.role.ms
    It isn’t set. It defaults to 168 hours.


In order of priority, meaning that configurations earlier in the list override the later entries:

log.retention.ms
    How long to keep a log file in milliseconds

log.retention.minutes
    How long to keep a log file in minutes

log.retention.hours
    Log file retention in hours

log.retention.bytes
    It could be specified with a longer rolling-time threshold to keep down I/O operations.

log.segment.bytes
    The setting governs how large an individual log segment can be.


 */
package com.mahfooz.kafka.storage.log.segment;

public class Segment {
}
