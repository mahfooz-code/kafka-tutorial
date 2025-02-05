/*
Metadata Requests

Metadata Requests are sent both by the Producer and Consumer to any broker and contain a list of topics it is 
interested in.
The broker will respond to metadata request with the partitions of the topic, replicas for the partition, and the 
leader replica.
It could be sent to any broker since all brokers have a copy of metadata cache with this information.
It needs to be refreshed intermittently by sending new ones, to know if the topic metadata changed or if the client 
received Not a Leader for Partition error.

*/
package com.mahfooz.kafka.request.type;

public class MetadataRequest {

}
