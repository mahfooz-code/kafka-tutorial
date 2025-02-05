from kafka import KafkaProducer
import hashlib


# Custom partitioner function
def custom_partitioner(key, all_partitions, available_partitions):
    return int(hashlib.md5(key).hexdigest(), 16) % len(available_partitions)


# Create producer with custom partitioner
producer = KafkaProducer(
    bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
    partitioner=custom_partitioner,
)

# Send message with key
producer.send("my-topic", key=b"custom-key", value=b"Custom Partition Message")
producer.close()
