from testcontainers.kafka import KafkaContainer
from kafka import KafkaProducer, KafkaConsumer
import pytest


@pytest.mark.skip("It is taking time so skip it")
def test_container():

    # Start Kafka container
    with KafkaContainer("confluentinc/cp-kafka:latest") as kafka:
        kafka_broker = kafka.get_bootstrap_server()

        # Produce a message
        producer = KafkaProducer(bootstrap_servers=kafka_broker)
        producer.send("test-topic", b"Test message")
        producer.flush()

        # Consume the message
        consumer = KafkaConsumer(
            "test-topic",
            bootstrap_servers=kafka_broker,
            auto_offset_reset="earliest",
            enable_auto_commit=True,
            group_id="test-group",
        )

        for message in consumer:
            print(f"Consumed: {message.value.decode('utf-8')}")
            break
