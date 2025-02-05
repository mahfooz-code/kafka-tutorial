
kafka-acl --topic test \
  --producer \
  --authorizer-properties zookeeper.connect=localhost:2181 \
  --add \
  --allow-principal User:alice
