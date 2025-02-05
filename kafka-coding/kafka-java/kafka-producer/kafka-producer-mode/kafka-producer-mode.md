# creating topic using docker in powershell
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic topic.mode `
        --partitions 2 `
        --replication-factor 1

# Async mode
    
# Fire forget
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic fire.forget `
        --partitions 2 `
        --replication-factor 1
# Sync
     docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic mode.sync `
        --partitions 2 `
        --replication-factor 1

