from kafka import KafkaConsumer, TopicPartition


def main():

    consumer = KafkaConsumer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        group_id="my-group",
    )

    # Assign a specific topic and partition
    partition = TopicPartition("my-topic", 0)
    consumer.assign([partition])

    # Set starting offset
    consumer.seek(partition, 5)  # Start from offset 5

    for message in consumer:
        print(f"Message: {message.value.decode('utf-8')}")


if __name__ == "__main__":
    main()
