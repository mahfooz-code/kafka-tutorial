The unit of data within Kafka is called a message.

A message is simply an array of bytes as far as Kafka is concerned, so the data contained within it does not have a specific format or meaning to Kafka

A message can have an optional piece of metadata, which is referred to as a key.

The key is also a byte array and, as with the message, has no specific meaning to Kafka.

Keys are used when messages are to be written to partition in a more controlled manner.

The simplest such scheme is to generate a consistent hash of the key and then select the partition number for that message by taking the result of the hash modulo the total number of partitions in the topic. 

This ensures that messages with the same key are always written to the same partition (provided that the partition count does not change).

For efficiency, messages are written into Kafka in batches. 

A batch is just a collection of messages, all of which are being produced to the same topic and partition.

