from kafka import KafkaConsumer, TopicPartition


def main():
    # Kafka configuration
    consumer = KafkaConsumer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        group_id="offset_checker",
    )

    # Specify the topic
    topic = "movies"

    # Get partitions for the topic
    partitions = consumer.partitions_for_topic(topic)

    # Retrieve the latest offsets for each partition
    for partition in partitions:
        tp = TopicPartition(topic, partition)
        consumer.assign([tp])
        beginning_offset = consumer.beginning_offsets([tp])[tp]
        print(f"Partition {partition}: Oldest offset = {beginning_offset}")

    consumer.close()


if __name__ == "__main__":
    main()
