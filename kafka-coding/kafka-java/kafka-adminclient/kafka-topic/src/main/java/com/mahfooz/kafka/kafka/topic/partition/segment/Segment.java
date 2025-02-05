/*
A message from the Producer is written to multiple partitions.

Each partition has numerous segments. When any of the above limits (size/retention period) exceeds while writing data to a partition segment, then that segment is closed, and a new one is started.

A segment to which Broker is currently writing is called an active segment.

An active segment will never be deleted until any of the segment's limits are exceeded.

A broker may open multiple file handles for every segment for all of its partitions, irrespective of active or inactive.

Each partition segment is stored in a single data file.
The file contains messages and their offsets.
Each message consists of its:

    key
    value
    offset
    message size
    checksum code - helps to detect message corruption.
    magic byte - indicates the version of the message format.
    compression codec - indicates message compression format (snappy, GZip or LZ4).
    timestamp - denotes the time message was sent from the producer or received by the consumer.

 */

package com.mahfooz.kafka.kafka.topic.partition.segment;

/**
 *
 * @author mahfooz
 */
public class Segment {
    public static void main(String [] args) {
    
    }
}
