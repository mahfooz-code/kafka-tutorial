from confluent_kafka.avro import AvroProducer, load

schema_registry_url = "http://localhost:8081"

if __name__ == "__main__":
    # Load schemas
    key_schema = load("./schema/key_schema.avsc")
    value_schema = load("./schema/value_schema.avsc")

    # Configure the producer
    producer_conf = {
        "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
        "schema.registry.url": schema_registry_url,
    }

    # Create Avro producer
    avro_producer = AvroProducer(
        config=producer_conf,
        default_key_schema=key_schema,
        default_value_schema=value_schema,
    )

    # Send Avro messages
    avro_producer.produce(
        topic="avro-topic",
        key={"id": 1},
        value={
            "name": "example",
            "age": 14,
            "email": "",
            "is_active": True,
            "created_at": 12121231,
        },
    )
    avro_producer.flush()
