import json

from kafka import KafkaConsumer

if __name__ == "__main__":
    # Kafka Consumer
    consumer = KafkaConsumer(
        "json_message",
        bootstrap_servers="localhost:19092,localhost:29092",
        max_poll_records=100,
        value_deserializer=lambda m: json.loads(m.decode("ascii")),
        auto_offset_reset="earliest",  # ,'smallest'
    )
    for message in consumer:
        print(json.loads(message.value))
