from confluent_kafka import SerializingProducer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.avro import AvroSerializer
from confluent_kafka.serialization import StringSerializer


def delivery_report(err, msg):
    if err:
        print(f"Message delivery failed: {err}")
    else:
        print(f"Message delivered to {msg.topic()} [{msg.partition()}]")


schema_registry_client = SchemaRegistryClient({"url": "http://localhost:8081"})

schema_str = schema_registry_client.get_latest_version("user-value").schema.schema_str

avro_serializer = AvroSerializer(
    schema_registry_client, schema_str, lambda obj, ctx: obj
)  # Serialization function

producer_conf = {
    "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
    "key.serializer": StringSerializer("utf_8"),
    "value.serializer": avro_serializer,
}

producer = SerializingProducer(producer_conf)

user = {"name": "Alice", "age": 30}
producer.produce(
    topic="avro-topic", key="alice", value=user, on_delivery=delivery_report
)
producer.flush()
