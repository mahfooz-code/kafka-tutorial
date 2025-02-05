# creating topic using docker
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic json.message `
        --partitions 2 `
        --replication-factor 1

# list the topic to check
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --list