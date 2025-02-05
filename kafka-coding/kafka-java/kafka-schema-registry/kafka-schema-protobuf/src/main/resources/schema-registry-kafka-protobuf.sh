docker run \
  --net=host \
  --rm \
  confluentinc/cp-kafka:5.4.0 \
  kafka-topics --create --topic protobuf-topic \
    --partitions 1 \
    --replication-factor 1 \
    --if-not-exists --zookeeper mtmdevhdoped01:32181


docker run \
  --net=host \
  --rm \
  confluentinc/cp-kafka:5.4.0 \
  kafka-topics --delete --topic protobuf-topic \
   --zookeeper mtmdevhdoped01:32181