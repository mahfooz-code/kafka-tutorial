# Broker config

## BROKER.ID

Every Kafka broker must have an integer identifier, which is set using the broker.id configuration.

By default, this integer is set to 0, but it can be any value.

The most important thing is that the integer must be unique within a single Kafka cluster.

## port

The configuration file starts Kafka with a listener on TCP port 9092.

This can be set to any available port by changing the port configuration parameter.

Keep in mind that if a port lower than 1024 is chosen, Kafka must be started as root.

## zookeeper.connect

The location of the Zookeeper used for storing the broker metadata is set using the `zookeeper.connect` configuration parameter.

The example configuration uses a Zookeeper running on port 2181 on the local host, which is specified as `localhost:2181`.

The format for this parameter is a semicolon-separated list of hostname:port/path strings, which include

1. `hostname`: the hostname or IP address of the Zookeeper server
2. `port`: the client port number for the server./path, an optional Zookeeper.
3. `path`: to use as a chroot environment for the Kafka cluster. If it is omitted, the root path is used.
    If a chroot path is specified and does not exist, it will be created by the broker when it starts up.

## log.dirs

Kafka persists all messages to disk, and these log segments are stored in the directories specified in the `log.dirs` configuration.

## num.recovery.threads.per.data.dir

When setting this parameter, remember that the number configured is per log directory specified with log.dirs.

This means that if num.recovery.threads.per.data.dir is set to 8, and there are 3 paths specified in log.dirs,
this is a total of 24 threads.

## auto.create.topics.enable

If you are managing topic creation explicitly, whether manually or through a provisioning system, you can set the `auto.create.topics.enable` configuration to false.

The default Kafka configuration specifies that the broker should automatically create

A topic under the following circumstances:

• When a producer starts writing messages to the topic
• When a consumer starts reading messages from the topic
• When any client requests metadata for the topic

## num.partitions

The num.partitions parameter determines how many partitions a new topic is created with, primarily when automatic topic creation is enabled (which is the default setting)

## log.retention.ms

The most common configuration for how long Kafka will retain messages is by time.

The default is specified in the configuration file using the log.retention.hours parameter, and it is set to 168 hours, or one week.
    However, there are two other parameters allowed, log.retention.minutes and log.retention.ms.

All three of these specify the same configuration—the amount of time after which messages may be deleted—but the recommended parameter to use is log.retention.ms, as the smaller unit size will take precedence if more than one is specified.

This will make sure that the value set for log.retention.ms is always the one used.

If more than one is specified, the smaller unit size will take precedence.

## log.retention.bytes

Another way to expire messages is based on the total number of bytes of messages retained.

This value is set using the log.retention.bytes parameter, and it is applied per-partition.

This means that if you have a topic with 8 partitions, and log.retention.bytes is set to 1 GB, the amount of data retained for the topic will be 8 GB at most. Note that all retention is performed for individual partitions, not the topic.

This means that should the number of partitions for a topic be expanded, the retention will also increase if `log.retention.bytes` is used.

## log.segment.bytes

The log-retention settings previously mentioned operate on log segments, not individual messages.

As messages are produced to the Kafka broker, they are appended to the current log segment for the partition. Once the log segment has reached the size specified by the log.segment.bytes parameter, which defaults to 1 GB, the log segment is closed and a new one is opened.

Once a log segment has been closed, it can be considered for expiration.

A smaller log-segment size means that files must be closed and allocated more often, which reduces the overall efficiency of disk writes.

## LOG.SEGMENT.MS

Another way to control when log segments are closed is by using the log.segment.ms parameter, which specifies the amount of time after which a log segment should be closed.

As with the log.retention.bytes and log.retention.ms parameters, log.segment.bytes and log.segment.ms are not mutually exclusive properties.

Kafka will close a log segment either when the size limit is reached or when the time limit is reached, whichever comes first.

By default, there is no setting for log.segment.ms, which results in only closing log segments by size.

## message.max.bytes

The Kafka broker limits the maximum size of a message that can be produced, configured by the `message.max.bytes` parameter, which defaults to 1000000, or 1 MB.

A producer that tries to send a message larger than this will receive an error back from the broker, and the message will not be accepted.

As with all byte sizes specified on the broker, this configuration deals with compressed message size, which means that producers can send messages that are much larger than this value uncompressed, provided they compress to under the configured message.max.bytes size.
