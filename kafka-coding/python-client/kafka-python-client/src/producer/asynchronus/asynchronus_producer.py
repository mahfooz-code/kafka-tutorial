from kafka import KafkaProducer


def on_send_success(record_metadata):
    print(
        f"Message sent to {record_metadata.topic}, partition {record_metadata.partition}, offset {record_metadata.offset}"
    )


def on_send_error(excp):
    print(f"Message delivery failed: {excp}")


def main():
    producer = KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092"
    )

    # Asynchronous send with callback
    future = producer.send("my-topic", b"Async message")
    future.add_callback(on_send_success)
    future.add_errback(on_send_error)

    producer.close()


if __name__ == "__main__":
    main()
