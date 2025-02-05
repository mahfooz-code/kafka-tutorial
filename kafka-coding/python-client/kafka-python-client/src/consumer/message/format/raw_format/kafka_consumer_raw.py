from kafka import KafkaConsumer

if __name__ == "__main__":
    bootstrap_servers = ["localhost:19091", "localhost:29091", "localhost:39091"]
    # To consume latest messages and auto-commit offsets
    consumer = KafkaConsumer(
        "raw_message", group_id="my-group", bootstrap_servers=bootstrap_servers
    )
    for message in consumer:
        print(
            "%s:%d:%d: key=%s value=%s"
            % (
                message.topic,
                message.partition,
                message.offset,
                message.key,
                message.value,
            )
        )
