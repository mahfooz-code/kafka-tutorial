from kafka import KafkaProducer


def main():

    # Create a producer with compression enabled
    producer = KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        compression_type="gzip",  # Available options: 'gzip', 'snappy', 'lz4', or 'zstd'
    )

    # Send compressed messages
    producer.send("message_compression", value=b"Compressed message 1")
    producer.send("message_compression", value=b"Compressed message 2")
    producer.send("message_compression", value=b"Compressed message 3")

    producer.close()


if __name__ == "__main__":
    main()
