from kafka import KafkaProducer
from kafka.errors import KafkaError


def main():

    # Create a producer with retry configuration
    producer = KafkaProducer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        retries=5,  # Retry 5 times in case of failure
        request_timeout_ms=1000,  # Timeout after 1000ms if not successful
    )

    # Send a message and handle errors
    try:
        future = producer.send("my-topic", value=b"Try sending this message")
        result = future.get(timeout=60)  # Wait for the result (block until success)
        print(f"result: {result}")
    except KafkaError as e:
        print(f"Failed to send message: {e}")
    finally:
        producer.close()


if __name__ == "__main__":
    main()
