/*
Fetch Requests

Fetch requests are sent by the consumer to Broker to read messages from an offset of a topic's leader partition. 
It is also used among Brokers to replicate partitions.

The client sends an upper limit (maximum amount of data the client can accept from the broker), and a lower limit 
(minimum amount of data needed for a data transfer to happen).

The Fetch request should have an upper limit. 
Otherwise, the broker will send a large amount of data making the client run out of memory.

Likewise, setting a minimum limit will ensure that broker sends the response only when there is sizable amount of data 
available to be sent to the client. 
Else, smaller amount of data processing happens resulting in resources being utilized inefficiently.

Fetch Requests
As soon as a leader replica receives a Fetch request, it validates the request by checking if the offset specified in the request exists for that particular partition.

If it is valid, the leader replica will read messages from that offset till the specified upper limit and return the messages to the client.
The broker will respond with an error message if it is an old one or if it does not exist.
To avoid any inconsistency, the leader replica always sends messages that are replicated to all its followers.

*/
package com.mahfooz.kafka.request.type;

public class FetchRequest {

}
