kafka-topics \
    --bootstrap-server localhost:9092 \
    --create \
    --topic users \
    --partitions 4 \
    --replication-factor 1


kafka-topics \
    --bootstrap-server localhost:9092 \
    --describe \
    --topic users
