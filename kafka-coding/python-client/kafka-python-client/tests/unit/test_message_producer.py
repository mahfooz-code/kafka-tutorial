from kafka import KafkaProducer, KafkaConsumer
from unittest.mock import MagicMock


def test_message_producer():
    # Mock KafkaProducer
    mock_producer = MagicMock(spec=KafkaProducer)
    mock_producer.send.return_value = MagicMock()

    # Test sending a message
    mock_producer.send("test-topic", b"Test message")
    mock_producer.send.assert_called_once_with("test-topic", b"Test message")

    # Mock KafkaConsumer
    mock_consumer = MagicMock(spec=KafkaConsumer)
    mock_consumer.poll.return_value = {"test-topic": [MagicMock(value=b"Test message")]}

    # Test consuming a message
    messages = mock_consumer.poll(timeout_ms=1000)
    assert messages["test-topic"][0].value == b"Test message"
