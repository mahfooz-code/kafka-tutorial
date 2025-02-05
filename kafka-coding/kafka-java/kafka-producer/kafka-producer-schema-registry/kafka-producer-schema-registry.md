# creating topic using docker in powershell
    docker exec -it broker1 `
        kafka-topics `
        --bootstrap-server localhost:9092 `
        --create `
        --topic schema.registry `
        --partitions 2 `
        --replication-factor 1

# using console producer
    docker exec -it schema-registry `
    kafka-avro-console-producer `
        --broker-list broker1:9092,broker2:9092 `
        --property schema.registry.url=http://localhost:8081 `
        --topic schema.registry  `
        --property value.schema='{\"type\":\"record\",\"name\":\"myrecord\",\"fields\":[{\"name\":\"f1\",\"type\":\"string\"}]}'
