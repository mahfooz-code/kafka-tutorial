/*

#Broker will delete all records which is older than 100 ms.

docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-kafka:5.4.0   \
  kafka-configs --zookeeper mtmdevhdoped01:32181 \
    --entity-type topics \
    --entity-name my-topic \
    --alter --add-config retention.ms=100

 */
package com.mahfooz.kafka.kafka.topic.record;

public class DeleteAllRecordsTopic {
}
