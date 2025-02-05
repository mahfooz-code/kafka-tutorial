from kafka import KafkaConsumer, TopicPartition

if __name__ == "__main__":
    # Define your Kafka configuration
    topic = "movies"
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:39092"

    # Create a Kafka consumer
    consumer = KafkaConsumer(bootstrap_servers=bootstrap_servers)

    # Get partitions for the topic
    partitions = consumer.partitions_for_topic(topic)
    if partitions is None:
        print(f"No partitions found for topic '{topic}'")
        exit(1)

    # Create TopicPartition objects for each partition
    topic_partitions = [TopicPartition(topic, p) for p in partitions]

    # Assign the partitions to the consumer
    consumer.assign(topic_partitions)

    # Seek to the end of each partition to get the latest offset
    consumer.seek_to_end()

    # Get the offsets for each partition
    offsets = {tp.partition: consumer.position(tp) for tp in topic_partitions}
    print(f"Offsets for topic '{topic}': {offsets}")
