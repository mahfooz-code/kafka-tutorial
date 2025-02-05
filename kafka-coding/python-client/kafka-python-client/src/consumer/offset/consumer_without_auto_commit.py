from kafka import KafkaConsumer


def main():

    consumer = KafkaConsumer(
        "my-topic",
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        auto_offset_reset="earliest",
        enable_auto_commit=False,  # Disable auto-commit
        group_id="my-group",
    )

    for message in consumer:
        print(f"Received message: {message.value.decode('utf-8')}")
        # Commit offsets manually
        consumer.commit()


if __name__ == "__main__":
    main()
