
kafka-console-producer --broker-list mtmdevhdoped01:9092 --topic move-apinotif-statuschangen \
  --property \
  value.serializer=custom.class.serialization.JsonSerializer


#--config CONFIGFILE, where CONFIGFILE is the full path to a file that contains the configuration options.

#--producer-property KEY=VALUE, where KEY is the configuration option name and VALUE is the value to set it to.
#This can be useful for producer options like message-batching configurations (such as linger.ms or batch.size).

#--key-serializer CLASSNAME

  #Specifies a message encoder class to be used to serialize the message key. 
  #This defaults to kafka.serializer.DefaultEncoder.

#--value-serializer CLASSNAME

  #Specifies a message encoder class to be used to serialize the message value.
  #This defaults to kafka.serializer.DefaultEncoder.

#--compression-codec STRING

  #Specify the type of compression to be used when producing messages.
  #This can be one of none, gzip, snappy, or lz4. The default value is gzip.

#--sync
  #Produce messages synchronously, waiting for each message to be acknowledged before sending the next one.

kafka-console-producer --broker-list mtmdevhdoped01.intl.vsnl.co.in:9092 --topic test
