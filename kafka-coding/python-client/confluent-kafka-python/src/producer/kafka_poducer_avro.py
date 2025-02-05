from confluent_kafka.avro import AvroProducer
from confluent_kafka.avro import load

schema_registry_url = "http://localhost:8081"

if __name__ == "__main__":
    # Load schemas
    key_schema = load("key_schema.avsc")
    value_schema = load("value_schema.avsc")

    # Configure the producer
    producer_conf = {
        "bootstrap.servers": "localhost:9092",
        "schema.registry.url": schema_registry_url,
    }

    # Create Avro producer
    avro_producer = AvroProducer(
        config=producer_conf,
        default_key_schema=key_schema,
        default_value_schema=value_schema,
    )

    # Send Avro messages
    avro_producer.produce(topic="test-topic", key={"id": 1}, value={"name": "example"})
    avro_producer.flush()
