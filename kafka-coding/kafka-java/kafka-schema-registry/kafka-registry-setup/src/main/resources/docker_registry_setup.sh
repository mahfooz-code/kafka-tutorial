#Starting zookeeper
docker run -d \
    --net=host \
    --name=zookeeper \
    -e ZOOKEEPER_HOST=mtmdevhdoped01 \
    -e ZOOKEEPER_CLIENT_PORT=32181 \
    -e ZOOKEEPER_TICK_TIME=2000 \
    confluentinc/cp-zookeeper:5.4.0

#Running kafka broker
docker run -d \
    --net=host \
    --name=kafka \
    -e KAFKA_ZOOKEEPER_CONNECT=mtmdevhdoped01:32181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://mtmdevhdoped01:29092 \
    confluentinc/cp-kafka:5.4.0

docker run -d \
    --net=host \
    --name=kafka2 \
    -e KAFKA_ZOOKEEPER_CONNECT=mtmdevhdoped01:32181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://mtmdevhdoped01:29093 \
    confluentinc/cp-kafka:5.4.0


docker run -d \
    --net=host \
    --name=kafka3 \
    -e KAFKA_ZOOKEEPER_CONNECT=mtmdevhdoped01:32181 \
    -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://mtmdevhdoped01:29094 \
    confluentinc/cp-kafka:5.4.0


docker run -d \
  --net=host \
  --name=schema-registry \
  -e SCHEMA_REGISTRY_KAFKASTORE_CONNECTION_URL=mtmdevhdoped01:32181 \
  -e SCHEMA_REGISTRY_HOST_NAME=mtmdevhdoped01 \
  -e SCHEMA_REGISTRY_LISTENERS=http://mtmdevhdoped01:8082 \
  confluentinc/cp-schema-registry:5.4.0


docker run \
  --net=host \
  --rm \
  confluentinc/cp-kafka:5.4.0 \
  kafka-topics --create --topic avro --partitions 1 --replication-factor 1 --if-not-exists --zookeeper mtmdevhdoped01:32181


docker run \
  --net=host \
  --rm \
  confluentinc/cp-kafka:5.4.0 \
  kafka-topics --zookeeper mtmdevhdoped01:32181 --list


docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-console-consumer -bootstrap-server mtmdevhdoped01:29092 --topic avro

docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
   kafka-topics --delete --zookeeper mtmdevhdoped01:32181 --topic avro


docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-run-class kafka.tools.GetOffsetShell \
  --broker-list mtmdevhdoped01:29092 \
  --topic avro \
  --time -1

docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-run-class kafka.tools.GetOffsetShell \
  --broker-list mtmdevhdoped01:29092 \
  --topic avro \
  --time -2

docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-configs --zookeeper localhost:2181 \
  --entity-type topics \
  --entity-name avro \
  --alter \
  --add-config retention.ms=604800000


#delete record using json
docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-delete-records \
  --bootstrap-server mtmdevhdoped01:29092 \
  --offset-json-file avro-topic.json
