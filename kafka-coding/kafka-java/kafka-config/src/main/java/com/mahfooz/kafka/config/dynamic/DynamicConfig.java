/*

Configurations can be overridden while the cluster is running for topics and for client quotas.
There is the intention to add more dynamic configurations in the future, which is why these changes have been put in a separate CLI tool, kafka-configs.sh.
 This allows you to set configurations for specific topics and client IDs. Once set, these configurations are permanent for the cluster. They are stored in Zookeeper, and they are read by each broker when it starts.
In tools and documentation, dynamic configurations like this are referred to as per-topic or per-client configurations, as well as overrides.

Overriding Topic Configuration Defaults
    
    kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster --alter --entity-type topics --entity-name \
    <topic name> --add-config <key>=<value>[,<key>=<value>...]

Overriding Client Configuration Defaults
    kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster--alter --entity-type topics --entity-name \
    my-topic --add-configretention.ms=3600000


 */

package com.mahfooz.kafka.config.dynamic;

/**
 *
 * @author Mahfooz Alam
 */
public class DynamicConfig {
    public static void main(String [] args ) {
    
    }
}
