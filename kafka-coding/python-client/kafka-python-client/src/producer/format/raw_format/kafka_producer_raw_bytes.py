import logging

from kafka import KafkaProducer

log = logging.getLogger(__name__)


def on_send_success(record_metadata):
    print(f"record_metadata.topic: {record_metadata.topic}")
    print(f"record_metadata.partition: {record_metadata.partition}")
    print(f"record_metadata.offset: {record_metadata.offset}")


def on_send_error(excp):
    log.error("I am an errback", exc_info=excp)
    # handle exception


def main():
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:39092"
    producer = KafkaProducer(bootstrap_servers=bootstrap_servers)

    # produce asynchronously with callbacks
    producer.send("raw_message", b"raw_bytes").add_callback(
        on_send_success
    ).add_errback(on_send_error)

    producer.flush()


if __name__ == "__main__":
    main()
