from confluent_kafka import Consumer

if __name__ == "__main__":
    config = {
        # User-specific properties that you must set
        "bootstrap.servers": "localhost:19092,localhost:29092",
        # 'sasl.username':     '<CLUSTER API KEY>',
        # 'sasl.password':     '<CLUSTER API SECRET>',
        # Fixed properties
        # 'security.protocol': 'SASL_SSL',
        # 'sasl.mechanisms':   'PLAIN',
        "group.id": "kafka-python-getting-started",
        # 'auto.offset.reset': 'earliest'
    }

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
