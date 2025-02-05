/*

The configuration properties for the Kafka server are set in the config/server.properties file.
The configuration properties are divided into sections.
Property

Description

Value

Section

broker.id

A unique broker ID for each broker. By default, only one broker is configured.

0

Server Basics

controlled.shutdown.retry.backoff.ms

Backoff time in ms between shutdown retries. Default value is 5000.

10000

Server Basics

auto.create.topics.enable

Whether to create topics automatically if not created before sending producer messages.

true

Server Basics

delete.topic.enable

Whether to enable topic deletion.

true

Server Basics

port

The port the socket server listens on.

9092

Socket Server Settings

host.name

Hostname the broker binds on. If not set, the server binds on all interfaces.

Not set

Socket Server Settings

advertised.host.name

Hostname the broker advertises to producers and consumers. If not set, the value of host.name is used.

Not set

Socket Server Settings

advertised.port

The port advertised to ZooKeeper. If not set, the port where the broker binds.

Not set

Socket Server Settings

num.network.threads

Number of threads handling network threads.

3

Socket Server Settings

num.io.threads

Number of input/output threads.

8

Socket Server Settings

socket.send.buffer.bytes

Send buffer used by the socket server.

102400

Socket Server Settings

socket.receive.buffer.bytes=102400

Receive buffer used by socket server.

102400

Socket Server Settings

socket.request.max.bytes

Maximum size of a socket server request.

104857600

Socket Server Settings

log.dirs

Directories (separated by ',' to store log files)

/tmp/kafka-logs

Log Basics

num.partitions

Default number of log partitions per topic.

1

Log Basics

num.recovery.threads.per.data.dir

Number of threads per data directory to be used for log recovery.

1

Log Basics

log.flush.interval.messages

Number of messages to accept before forcing a flush of data to disk.

10000

Log Flush Policy

log.flush.interval.ms

Maximum amount of time a log message can be retained before flushing.

1000

Log Flush Policy

log.retention.hours

Minimum age of a log file to be eligible for deletion.

168

Log Retention Policy

log.retention.bytes

The minimum size of logs to be retained. Logs are not pruned below the setting.

1073741824

Log Retention Policy

log.segment.bytes

Maximum size of a log segment file.

1073741824

Log Retention Policy

log.retention.check.interval.ms

The interval at which log segments are checked to determine if they need to be deleted.

300000

Log Retention Policy

log.cleaner.enable

Whether to enable log cleaner, which marks individual logs for log compaction.

false

Log Retention Policy

zookeeper.connection.timeout.ms

Timeout for connecting to ZooKeeper.

6000

ZooKeeper

 */

package com.mahfooz.kafka.config.server;

/**
 *
 * @author Mahfooz Alam
 */
public class ServerConfig {
    public static void main(String [] args ) {
    
    }
}
