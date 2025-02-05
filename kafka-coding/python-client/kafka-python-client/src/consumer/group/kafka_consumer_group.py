from kafka import KafkaConsumer, TopicPartition

if __name__ == "__main__":
    bootstrap_servers = "localhost:19091,localhost:29091,localhost:39091"

    # Create a Kafka consumer
    consumer = KafkaConsumer(bootstrap_servers=bootstrap_servers)
    consumer.assign([TopicPartition("movies", 0)])
    for msg in consumer:
        print(msg)
