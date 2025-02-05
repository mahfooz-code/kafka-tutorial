from kafka import KafkaConsumer
from kafka import TopicPartition
import time

# Get the timestamp corresponding to a specific time (e.g., 1 minute ago)
timestamp = int((time.time() - 60) * 1000)  # In milliseconds

consumer = KafkaConsumer(bootstrap_servers="localhost:9092", group_id="my-group")

# Assign to a specific topic and partition
partition = TopicPartition("my-topic", 0)
consumer.assign([partition])

# Seek to the offset corresponding to the timestamp
consumer.seek(
    partition, consumer.offsets_for_times({partition: timestamp})[partition].offset
)

# Consume messages starting from that point
for message in consumer:
    print(f"Message: {message.value.decode('utf-8')}")
