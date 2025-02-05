/*

Compression is done on a set of messages and represented as a single compressed message.
A ByteBufferMessageSet contains both compressed and uncompressed messages.
A new compression-attribute byte is introduced in the message header to distinguish compressed and uncompressed messages.
This attribute type indicates the changes in storage byte format and network byte format.
The new format header is versioned as magic byte value of 1.
The batch of messages that are compressed will remain as compressed in the log files.
Represented as a single message to the consumer that decompress it later.
The decompression overhead will be on the consumer side only.

compression.type: This is used to set the compression format for the messages sent from the Producer. 
The default value is None - meaning no compression. 
For compressing messages before sending to brokers, gzip, snappy, or lz4 algorithm can be specified.

 */
package com.mahfooz.kafka.producer.compress;

public class MessageCompression {
}
