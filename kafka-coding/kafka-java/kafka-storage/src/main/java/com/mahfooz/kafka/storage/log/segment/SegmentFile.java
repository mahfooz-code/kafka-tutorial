/*
Segment File
Each partition segment is stored in a single data file. The file contains messages and their offsets.

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
package com.mahfooz.kafka.storage.log.segment;

public class SegmentFile {

}
