from kafka import KafkaConsumer, TopicPartition

if __name__ == "__main__":
    # Define your Kafka configuration
    topic = "movies"
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:39092"
    partition = 0  # Specify the partition you want to assign

    # Create a Kafka consumer
    consumer = KafkaConsumer(
        bootstrap_servers=bootstrap_servers, auto_offset_reset="earliest"
    )

    # Create a TopicPartition object
    topic_partition = TopicPartition(topic, partition)

    # Assign the partition to the consumer
    consumer.assign([topic_partition])

    # Poll for new messages
    try:
        while True:
            msg = consumer.poll(timeout_ms=1000)
            if msg is None:
                continue
            for tp, messages in msg.items():
                for message in messages:
                    print(
                        f"""Received message: {message.value.decode("utf-8")}
                        from partition: {tp.partition}
                        offset: {message.offset}"""
                    )
    except KeyboardInterrupt:
        pass
    finally:
        # Close down consumer to commit final offsets.
        consumer.close()
