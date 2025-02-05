from kafka import KafkaConsumer

# Create a consumer with manual offset management
consumer = KafkaConsumer(
    "my-topic",
    bootstrap_servers="localhost:9092",
    group_id="my-group",
    enable_auto_commit=False,  # Disable auto-commit
    auto_offset_reset="earliest",
)

# Manually commit offsets
for message in consumer:
    print(f"Received message: {message.value.decode('utf-8')}")

    # Manually commit the offset after processing the message
    consumer.commit()
