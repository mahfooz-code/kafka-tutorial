from confluent_kafka import Consumer

if __name__ == "__main__":
    config = {
        # User-specific properties that you must set
        "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
        # 'sasl.username':     '<CLUSTER API KEY>',
        # 'sasl.password':     '<CLUSTER API SECRET>',
        # Fixed properties
        # 'security.protocol': 'SASL_SSL',
        # 'sasl.mechanisms':   'PLAIN',
        "group.id": "consumer-batch",
        # 'auto.offset.reset': 'earliest'
    }

    # Create Consumer instance
    consumer = Consumer(config)
    consumer.subscribe(["batch-topic"])
    messages: list[str] = []
    try:
        while True:
            msg = consumer.poll(1.0)  # Timeout in seconds
            if msg is None:
                continue
            if msg.error():
                print(f"Consumer error: {msg.error()}")
                continue

            print(f"Received message: {msg.value().decode('utf-8')} from {msg.topic()}")

    except KeyboardInterrupt:
        print("Stopping consumer...")
    finally:
        consumer.close()
