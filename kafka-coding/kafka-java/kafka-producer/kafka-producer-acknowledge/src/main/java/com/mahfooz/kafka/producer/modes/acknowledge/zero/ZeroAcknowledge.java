/*

If acks=0, the producer will not wait for a reply from the broker before assuming the message was sent successfully.
This means that if something went wrong and the broker did not receive the message, the producer will not know about
it and the message will be lost.

However, because the producer is not waiting for any response from the server, it can send messages as fast as the
network will support, so this setting can be used to achieve very high throughput.
 */
package com.mahfooz.kafka.producer.modes.acknowledge.zero;

public class ZeroAcknowledge {
}
