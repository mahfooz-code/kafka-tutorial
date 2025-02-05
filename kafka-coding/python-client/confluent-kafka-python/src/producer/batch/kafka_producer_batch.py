from confluent_kafka import Producer

if __name__ == "__main__":
    config = {
        # User-specific properties that you must set
        "bootstrap.servers": "localhost:19092,localhost:29092,localhost:39092",
        # 'sasl.username':     '<CLUSTER API KEY>',
        # 'sasl.password':     '<CLUSTER API SECRET>',
        # Fixed properties
        # 'security.protocol': 'SASL_SSL',
        # 'sasl.mechanisms':   'PLAIN',
        # 'acks':              'all'
    }

    # Create Producer instance
    producer = Producer(config)

    messages = [("key1", "value1"), ("key2", "value2"), ("key3", "value3")]
    for key, value in messages:
        producer.produce("batch-topic", key=key, value=value)
    producer.flush()
