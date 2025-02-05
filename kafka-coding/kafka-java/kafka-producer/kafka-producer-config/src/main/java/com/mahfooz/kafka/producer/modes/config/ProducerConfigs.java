/*
A Kafka producer has three mandatory properties:

    a)  bootstrap.servers
        
        List of host:port pairs of brokers that the producer will use to establish initial connection to the 
        Kafka cluster.

    b)  key.serializer

        Name of a class that will be used to serialize the keys of the records we will produce to Kafka.
        key.serializer should be set to a name of a class that implements the org.apache.kafka.common.serialization.Serializer
        interface.
        The Kafka client package includes ByteArraySerializer, StringSerializer, and IntegerSerializer, so if you use
        common types, there is no need to implement your own serializers.
        Setting key.serializer is required even if you intend to send only values.
    
    c)  value.serializer

        Name of a class that will be used to serialize the values of the records we will produce to Kafka.
        The same way you set key.serializer to a name of a class that will serialize the message key object to a 
        byte array, you set value.serializer to a class that will serialize the message value object.

    d)  buffer.memory

        This sets the amount of memory the producer will use to buffer messages waiting to be sent to brokers.
        If messages are sent by the application faster than they can be delivered to the server, the producer may run
        out of space and additional send() calls will either block or throw an exception, based on the block.on.buffer.full
         parameter (replaced with max.block.ms in release 0.9.0.0, which allows blocking for a certain time and then throwing
         an exception).

    e)  compression.type

        By default, messages are sent uncompressed.
        This parameter can be set to snappy, gzip, or lz4, in which case the corresponding compression algorithms
        will be used to compress the data before sending it to the brokers.
        Snappy compression was invented by Google to provide decent compression ratios with low CPU overhead and
        good performance, so it is recommended in cases where both performance and bandwidth are a concern.
        Gzip compression will typically use more CPU and time but results in better compression ratios, so it is
        recommended in cases where network bandwidth is more restricted.
        By enabling compression, you reduce network utilization and storage, which is often a bottleneck when sending
        messages to Kafka.

    f)  retries

        When the producer receives an error message from the server, the error could be transient (e.g., a lack of leader for a partition).
        * In this case, the value of the retries parameter will control how many times the producer will retry sending the message before giving up 
        * and notifying the client of an issue.
        * By default, the producer will wait 100ms between retries, but you can control this using the retry.backoff.ms parameter.
        * We recommend testing how long it takes to recover from a crashed broker (i.e., how long until all partitions get new leaders) and setting 
        * the number of retries and delay between them such that the total amount of time spent retrying will be longer than the time it takes
        * the Kafka cluster to recover from the crash—otherwise, the producer will give up too soon. Not all errors will be retried by the producer.
        * Some errors are not transient and will not cause retries (e.g., "message too large error).
        * In general, because the producer handles retries for you, there is no point in handling retries within your own application logic.
        * You will want to focus your efforts on handling nonretriable errors or cases where retry attempts were exhausted


    g)  batch.size

        When multiple records are sent to the same partition, the producer will batch them together.
        This parameter controls the amount of memory in bytes (not messages!) that will be used for each batch.
        When the batch is full, all the messages in the batch will be sent.
        However, this does not mean that the producer will wait for the batch to become full.
        The producer will send half-full batches and even batches with just a single message in them.
        Therefore, setting the batch size too large will not cause delays in sending messages; it will just use more
        memory for the batches.
        Setting the batch size too small will add some overhead because the producer will need to send messages more
        frequently.

    h)  linger.ms

        linger.ms controls the amount of time to wait for additional messages before sending the current batch. KafkaProducer sends a batch of messages either when the current batch is full or when the linger.ms limit is reached. By default, the producer will send messages as soon as there is a sender thread available to send them, even if there’s just one message in the batch. By setting linger.ms higher than 0, we instruct the producer to wait a few milliseconds to add additional messages to the batch before sending it to the brokers. This increases latency but also increases throughput (because we send more messages at once, there is less overhead per message).


    i)  client.id

        This can be any string, and will be used by the brokers to identify messages sent from the client. It is used in logging and metrics, and for quotas.

    j)  max.in.flight.requests.per.connection
        This controls how many messages the producer will send to the server without receiving responses. Setting this high can increase memory usage while improving throughput, but setting it too high can reduce throughput as batching becomes less efficient. Setting this to 1 will guarantee that messages will be written to the broker in the order in which they were sent, even when retries occur.

    k)  metadata.broker.list=

    l)  producer.type=sync or async

    m)  request.requied.acks=0|1|-1
        acks=0:

        This setting can be used for achieving high throughput and is the fastest one.
        When acks is set to 0, the Producer will not wait for an acknowledgment from any of the brokers to assume the message is sent successfully.
        This setting provides the least durability as there could be message loss - sometimes the message is not sent to the broker, and the Producer will not know about it.

        acks=1:

        With this setting, the Producer will receive a success response from the broker as soon as the message is written to the leader replica.
        If the leader replica crashes in between and the message could not be written to the leader, the producer will receive an error response, and it will resend the message avoiding loss of data.
        There can still be a message loss if the leader crashes and a replica gets elected as a new leader without this message.
        With acks = 1, there will be a tradeoff between throughput and latency depending on whether the send() method sends data synchronously or asynchronously.
        If the data is sent synchronously, waiting for a reply from the server using the FutureObject get() method, there will be an increase in latency.
        If the data is sent asynchronously with a callback, the throughput depends on the number of messages sent from the Producer before receiving a reply from the server.

        acks=all:

        The Producer will receive a success response from the broker, once all in-sync replicas received the message.
        This setting ensures the safest message publish, as it ensures the data had reached at more than one replicas in the cluster.
        But it has the highest latency as the broker is waiting for the message to be written to more than one replicas.

    n)  serializer.class=

    o)  TIMEOUT.MS, REQUEST.TIMEOUT.MS, AND METADATA.FETCH.TIMEOUT.MS
        These parameters control how long the producer will wait for a reply from the server when sending data
        (request.timeout.ms) and when requesting metadata such as the current leaders for the partitions we are writing
        to (metadata.fetch.timeout.ms).
        If the timeout is reached without reply, the producer will either retry sending or respond with an error
        (either through exception or the send callback).
        timeout.ms controls the time the broker will wait for in-sync replicas to acknowledge the message in order to
        meet the acks configuration the broker will return an error if the time elapses without the necessary acknowledgments.
    p)  MAX.BLOCK.MS

        This parameter controls how long the producer will block when calling send() and when explicitly requesting metadata via partitionsFor(). Those methods block when the producer’s send buffer is full or when metadata is not available. When max.block.ms is reached, a timeout exception is thrown.

    q)  MAX.REQUEST.SIZE
        This setting controls the size of a produce request sent by the producer. It caps both the size of the largest message that can be sent and the number of messages that the producer can send in one request. For example, with a default maximum request size of 1 MB, the largest message you can send is 1 MB or the producer can batch 1,024 messages of size 1 KB each into one request. In addition, the broker has its own limit on the size of the largest message it will accept (message.max.bytes). It is usually a good idea to have these configurations match, so the producer will not attempt to send messages of a size that will be rejected by the broker.

    s)  RECEIVE.BUFFER.BYTES AND SEND.BUFFER.BYTES
        These are the sizes of the TCP send and receive buffers used by the sockets when writing and reading data.
        If these are set to -1, the OS defaults will be used. It is a good idea to increase those when producers or consumers communicate with brokers in a different datacenter because those network links typically have higher latency and lower bandwidth.

 */
package com.mahfooz.kafka.producer.modes.config;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 *
 * @author Mahfooz Alam
 */
public class ProducerConfigs {

    private static final String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws InterruptedException, IOException {

        // Generate total consecutive events starting with ufoId
        long total = Long.parseLong("2");
        long ufoId = Math.round(Math.random() * Integer.MAX_VALUE);
        Properties props = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props);) {
            for (long i = 0; i < total; i++) {
                String key = Long.toString(ufoId++);
                long runtime = new Date().getTime();
                double latitude = (Math.random() * (2 * 85.05112878)) - 85.05112878;
                double longitude = (Math.random() * 360.0) - 180.0;
                String msg = runtime + "," + latitude + "," + longitude;

                ProducerRecord<String, String> data = new ProducerRecord<>("positions", key, msg);
                producer.send(data);
                long wait = Math.round(Math.random() * 25);
                Thread.sleep(wait);
            }

        }

        System.out.println(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
        System.out.println(ProducerConfig.ACKS_CONFIG);
        System.out.println(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG);
    }
}
