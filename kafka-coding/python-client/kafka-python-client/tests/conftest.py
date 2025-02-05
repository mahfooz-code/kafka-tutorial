import pytest
from kafka import KafkaConsumer, KafkaProducer


@pytest.fixture(scope="module")
def producer():
    return KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092"
    )


@pytest.fixture(scope="module")
def consumer():
    return KafkaConsumer(
        "test_topic",
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        auto_offset_reset="earliest",
    )
