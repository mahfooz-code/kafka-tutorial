from kafka import KafkaConsumer, KafkaAdminClient

# Create a consumer
consumer = KafkaConsumer(
    "my-topic",
    bootstrap_servers="localhost:9092",
    group_id="my-group",
    auto_offset_reset="earliest",
)

# Create an admin client to access consumer group metadata
admin = KafkaAdminClient(bootstrap_servers="localhost:9092")

# Fetch lag data for the consumer group
lag_data = admin.list_consumer_groups()
print(f"Consumer Group Lag: {lag_data}")

# Monitor consumer group lag
for message in consumer:
    print(f"Message: {message.value.decode('utf-8')}")
