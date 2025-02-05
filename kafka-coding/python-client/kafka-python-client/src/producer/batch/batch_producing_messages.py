from kafka import KafkaProducer


def main():
    # Create the producer
    producer = KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        batch_size=16384,  # Maximum batch size in bytes
        linger_ms=5,  # Wait up to 5ms to batch before sending
    )

    # Send multiple messages
    producer.send("my-topic", b"Message 1")
    producer.send("my-topic", b"Message 2")
    producer.send("my-topic", b"Message 3")

    # Flush and close the producer
    producer.flush()  # Ensure all messages are sent before closing
    producer.close()


if __name__ == "__main__":
    main()
