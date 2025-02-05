#
docker exec -it broker \
  kafka-topics --zookeeper mtmdevhdoped01:12181/ \
    --create --topic proto-topic \
    --replication-factor 1 \
    --partitions 1 \
    --if-not-exists