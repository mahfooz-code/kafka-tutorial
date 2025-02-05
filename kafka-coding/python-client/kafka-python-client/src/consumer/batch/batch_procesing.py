import json
from kafka import KafkaConsumer

consumer = KafkaConsumer(
    "my-topic",
    bootstrap_servers="localhost:9092",
    auto_offset_reset="earliest",
    group_id="my-group",
    value_deserializer=lambda v: json.loads(v.decode("utf-8")),
)

for message in consumer:
    print(f"Received JSON message: {message.value}")
