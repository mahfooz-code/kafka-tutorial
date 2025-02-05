import json
import logging

from kafka import KafkaProducer
from kafka.errors import KafkaError

log = logging.getLogger(__name__)

if __name__ == "__main__":
    bootstrap_servers = ["localhost:19092", "localhost:29092", "localhost:39092"]

    # Asynchronous by default
    producer = KafkaProducer(
        bootstrap_servers=bootstrap_servers,
        value_serializer=lambda m: json.dumps(m).encode("ascii"),
    )
    future = producer.send("movies", {"key": "value"})

    # Block for 'synchronous' sends
    try:
        record_metadata = future.get(timeout=10)
    except KafkaError as error:
        log.exception(str(error))

    print(f"record_metadata.topic: {record_metadata.topic}")
    print(f"record_metadata.partition: {record_metadata.partition}")
    print(f"record_metadata.offset: {record_metadata.offset}")

    # block until all async messages are sent
    producer.flush()
