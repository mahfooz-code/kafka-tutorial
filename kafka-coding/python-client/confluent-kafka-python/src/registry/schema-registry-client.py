from confluent_kafka import SerializingProducer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.avro import AvroSerializer


def main():
    schema_registry_conf = {"url": "http://localhost:8081"}
    schema_registry_client = SchemaRegistryClient(schema_registry_conf)

    value_schema_str = """
    {
        "type": "record",
        "name": "User",
        "fields": [
            {"name": "name", "type": "string"},
            {"name": "age", "type": "int"}
        ]
    }
    """

    value_serializer = AvroSerializer(
        schema_registry_client=schema_registry_client, schema_str=value_schema_str
    )

    avro_producer = SerializingProducer(
        {
            "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
            "value.serializer": value_serializer,
        }
    )

    avro_producer.produce(topic="avro_topic", value={"name": "Alice", "age": 30})
    avro_producer.flush()


if __name__ == "__main__":
    main()
