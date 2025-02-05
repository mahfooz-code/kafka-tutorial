/*

The broker, topic, and partition information are maintained in Zookeeper. 

In previous versions of Kafka, it was possible to specify per-topic overrides for these configurations in the broker
configuration using the parameters.

    log.retention.hours.per.topic
    log.retention.bytes.per.topic
    log.segment.bytes.per.topic

These parameters are no longer supported, and overrides must be specified using the administrative tools.

    num.partitions
    The num.partitions parameter determines how many partitions a new topic is created with, primarily when automatic topic
    creation is enabled (which is the default setting).

log.retention.ms

log.retention.hours

log.retention.minutes

log.retention.bytes

log.segment.bytes

log.segment.ms

message.max.bytes


 */

package com.mahfooz.kafka.kafka.topic.config;

/**
 *
 * @author Mahfooz Alam
 */
public class TopicConfig {
    public static void main(String [] args ) {
    
    }
}
