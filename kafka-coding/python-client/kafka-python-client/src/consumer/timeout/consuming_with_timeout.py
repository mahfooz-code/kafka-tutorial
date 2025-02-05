from kafka import KafkaConsumer


def main():
    consumer = KafkaConsumer(
        "my-topic",
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        auto_offset_reset="earliest",
        consumer_timeout_ms=1000,  # Stop after 1 second if no messages
    )

    for message in consumer:
        print(f"Message: {message.value.decode('utf-8')}")


if __name__ == "__main__":
    main()
