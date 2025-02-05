docker run   \
  --net=host   \
  --rm   \
  confluentinc/cp-schema-registry:5.4.0 \
  kafka-protobuf-console-producer \
  --broker-list mtmdevhdoped01:29092 \
  --property schema.registry.url=http://mtmdevhdoped01:8082 \
  --topic protobuf-topic \
  --property value.schema='syntax = "proto3"; message MyRecord { string f1 = 1; }'
