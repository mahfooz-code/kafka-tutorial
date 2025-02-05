import time

import pytest

from util.kafka_util import get_consumer, get_producer


@pytest.fixture
def producer():
    return get_producer()


@pytest.fixture
def consumer():
    return get_consumer("test_topic")


def test_kafka_produce_consume(producer, consumer):
    test_message = "Hello Kafka!"

    # Produce a message
    producer.produce("test_topic", key="test_key", value=test_message)
    producer.flush()

    # Wait for Kafka to process the message
    time.sleep(2)

    # Consume the message
    msg = consumer.poll(5.0)
    assert msg is not None, "No message received"
    assert msg.value().decode("utf-8") == test_message, "Message content mismatch"
