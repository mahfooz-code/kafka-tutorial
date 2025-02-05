
# Dynamic configurations like this are referred to as per-topic or per-client configurations, as well as overrides.

#Overriding Topic Configuration Defaults

kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster \
  --alter --entity-type topics --entity-name <topic name> \
  --add-config <key>=<value>[,<key>=<value>...]

kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster \
  --alter --entity-type topics --entity-name my-topic --add-config \
  retention.ms=3600000

#Overriding Client Configuration Defaults
kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster \
  --alter --entity-type clients --entity-name <client ID> \
  --add-config <key>=<value>[,<key>=<value>...]

#
kafka-configs.sh --zookeeper zoo1.example.com:2181/kafka-cluster \
  --alter --entity-type topics --entity-name my-topic \
  --delete-config retention.ms


###########################DOCKER########################################
docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-configs \
  --zookeeper mtmdevhdoped01:32181/ \
  --alter --entity-type topics \
  --entity-name avro \
  --add-config \
  delete.topic.enable=true