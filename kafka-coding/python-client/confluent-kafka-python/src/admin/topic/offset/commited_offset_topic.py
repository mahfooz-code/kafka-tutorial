from confluent_kafka import Consumer, TopicPartition


def main():
    # Configuration for the consumer
    consumer_conf = {
        "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
        "group.id": "python-consumer-group",
        "enable.auto.commit": False,  # Disable auto commit to manage offsets manually
        "auto.offset.reset": "earliest",
    }

    # Create a consumer instance
    consumer = Consumer(consumer_conf)

    # Define the topic and partitions
    topic = "purchases"
    consumer.assign(
        [TopicPartition(topic, partition) for partition in range(0, 3)]
    )  # Assuming 3 partitions

    # Get offsets
    partitions = consumer.assignment()
    for partition in partitions:
        committed_offset = consumer.committed(partition)  # Get committed offset
        position_offset = consumer.position(partition)  # Get current position
        print(
            f"""Partition: {partition.partition},
            Committed Offset: {committed_offset.offset},
            Position Offset: {position_offset.offset}"""
        )

    # Close the consumer
    consumer.close()


if __name__ == "__main__":
    main()
