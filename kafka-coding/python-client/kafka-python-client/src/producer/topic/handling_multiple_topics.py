from kafka import KafkaConsumer


def main():

    consumer = KafkaConsumer(
        "topic1",
        "topic2",  # Multiple topics
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        group_id="my-group",
        auto_offset_reset="earliest",
    )

    for message in consumer:
        print(f"Topic: {message.topic}, Message: {message.value.decode('utf-8')}")


if __name__ == "__main__":
    main()
