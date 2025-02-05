from confluent_kafka import Consumer, Producer

KAFKA_BROKER = "localhost:19092,localhost:29092,localhost:39092"


# Kafka Producer
def get_producer():
    return Producer({"bootstrap.servers": KAFKA_BROKER})


# Kafka Consumer
def get_consumer(topic):
    consumer = Consumer(
        {
            "bootstrap.servers": KAFKA_BROKER,
            "group.id": "test_group",
            "auto.offset.reset": "earliest",
        }
    )
    consumer.subscribe([topic])
    return consumer
