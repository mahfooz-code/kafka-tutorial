/*
buffer.memory: 
This is used to set the total amount of memory the Producer can use, to buffer messages waiting to be sent to Broker.
The producer will block sends for max.block.ms time and throw an exception, 
if the messages are sent faster than the brokers could receive them.
*/
package com.mahfooz.kafka.producer.memory;

public class BufferedMemory {

}
