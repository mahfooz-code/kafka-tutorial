from kafka import KafkaProducer


def main():
    msgpack = {"id": 1, "name": "Mahfooz"}
    # encode objects via msgpack
    producer = KafkaProducer(
        bootstrap_servers=["localhost:19092", "localhost:29092", "localhost:39092"]
    )
    producer.send("topic2", msgpack)

    # produce asynchronously
    for _ in range(100):
        producer.send("topic3", b"msg")


if __name__ == "__main__":
    main()
