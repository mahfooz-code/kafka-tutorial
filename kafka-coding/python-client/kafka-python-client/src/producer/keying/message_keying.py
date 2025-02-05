from kafka import KafkaProducer


def main():

    # Create a producer
    producer = KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092"
    )

    # Send messages with keys to ensure ordered processing within a partition
    producer.send("message_keying", key=b"key1", value=b"Message 1")
    producer.send("message_keying", key=b"key1", value=b"Message 2")
    producer.send("message_keying", key=b"key2", value=b"Message 3")
    producer.close()


if __name__ == "__main__":
    main()
