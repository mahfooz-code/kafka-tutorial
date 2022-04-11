# Creating topic
    kafka-topics \
    --bootstrap-server localhost:19092,localhost:29092 \
    --create \
    --topic users \
    --partitions 2 \
    --replication-factor 1

# Creating topic using docker
    docker exec -it broker1 \
    kafka-topics \
    --bootstrap-server localhost:9092 \
    --create \
    --topic users \
    --partitions 2 \
    --replication-factor 1

