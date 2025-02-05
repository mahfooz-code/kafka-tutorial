#By default, it outputs the raw bytes in the message with no formatting (using the DefaultFormatter).
#It is very important to use a consumer that is the same version as your Kafka cluster
#Older console consumers can potentially damage the cluster by interacting with Zookeeper in incorrect ways.

#OLD
kafka-console-consumer.sh \
    --zookeeper zoo1.example.com:2181/kafka-cluster \
    --topic my-topic

#NEW
    kafka-console-consumer \
    --bootstrap-server mtmdevhdoped01:9092 \
    --topic customerCountries

#The first is to provide a consumer configuration file by specifying --consumer.config CONFIGFILE, 
#where _CONFIGFILE_ is the full path to a file that contains the configuration options.


#The other way is to specify the options on the command line with one or more arguments of the form --consumer-property
#KEY=VALUE, where _KEY_ is the configuration option name and _VALUE_ is the value to set it to

#There is a --property command-line option for both the console consumer and the console producer, but this should not 
#be confused with the --consumer-property and --producer-property options. 
#The --property option is only used for passing configurations to the message formatter, and not the client itself.

#--formatter CLASSNAME Specifies a message formatter class to be used to decode the messages. 
#This defaults to kafka.tools.DefaultFormatter.

#--from-beginning Consume messages in the topic(s) specified from the oldest offset. 
#Otherwise, consumption starts from the latest offset.


#--max-messages NUM Consume at most NUM messages before exiting.

#--partition NUM Consume only from the partition with ID NUM (requires the new consumer).

#Message formatter options
There are three message formatters available to use besides the default:

#kafka.tools.LoggingMessageFormatter
#Outputs messages using the logger, rather than standard out. Messages are printed at the INFO level, and include the timestamp, key, and value.

#kafka.tools.ChecksumMessageFormatter

#Prints only message checksums.

#kafka.tools.NoOpMessageFormatter
#Consumes messages but does not output them at all.

#The kafka.tools.DefaultMessageFormatter also has several useful options that can be passed using the --property command-line option:
#print.timestamp
#Set to “true” to display the timestamp of each message (if available).
#print.key
#Set to “true” to display the message key in addition to the value.
#key.separator
#Specify the delimiter character to use between the message key and message
#value when printing.
#line.separator
#Specify the delimiter character to use between messages.
#key.deserializer
#Provide a class name that is used to deserialize the message key before printing.
#value.deserializer
#Provide a class name that is used to deserialize the message value before printing.

kafka-console-consumer.sh \
    --zookeeper zoo1.example.com:2181/kafka-cluster \
    --topic __consumer_offsets \
    --formatter 'kafka.coordinator.GroupMetadataManager$OffsetsMessageFormatter' \
    --max-messages 1

