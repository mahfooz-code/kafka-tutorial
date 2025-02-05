import json

from kafka import KafkaConsumer

if __name__ == "__main__":
    consumer = KafkaConsumer(
        "json_message_1",
        bootstrap_servers="localhost:19091",
        max_poll_records=100,
        value_deserializer=lambda m: json.loads(m.decode("ascii")),
        auto_offset_reset="earliest",
    )
    for message in consumer:
        print(message.value)
