from confluent_kafka import Consumer


def set_consumer_configs():
    config_dict = {}
    config_dict["group.id"] = "consumer_group"
    config_dict["auto.offset.reset"] = "earliest"
    config_dict["enable.auto.commit"] = True
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    return config_dict


if __name__ == "__main__":
    config = set_consumer_configs()

    # Create Consumer instance
    consumer = Consumer(config)

    # Subscribe to topic
    topic = "purchases"
    consumer.subscribe([topic])

    # Poll for new messages from Kafka and print them.
    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                # Initial message consumption may take up to
                # `session.timeout.ms` for the consumer group to
                # rebalance and start consuming
                print("Waiting...")
            elif msg.error():
                print(f"ERROR: {msg.error()}")
            else:
                # Extract the (optional) key and value, and print.
                print(
                    f"""Consumed event from topic {msg.topic()}:
                    key = {msg.key().decode("utf-8"):12}
                    value = {msg.value().decode("utf-8"):12}"""
                )
    except KeyboardInterrupt:
        pass
    finally:
        # Leave group and commit final offsets
        consumer.close()
