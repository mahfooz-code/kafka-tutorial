# creating topic
    docker exec -it broker1 \
    kafka-topics \
    --bootstrap-server localhost:9092 \
    --create \
    --topic users \
    --partitions 2 \
    --replication-factor 1

# 
    docker exec -it broker1 \
        kafka-topics \
        --bootstrap-server localhost:9092 \
        --create \
        --topic positions \
        --partitions 2 \
        --replication-factor 1

# creating topic using docker in powershell
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic positions `
        --partitions 2 `
        --replication-factor 1