def test_kafka_produce_consume(producer, consumer):
    # Produce a message to the Kafka topic
    producer.send("test_topic", b"test_message")
    producer.flush()

    # Consume the message from the Kafka topic
    for message in consumer:
        assert message.value == b"test_message"
        break
