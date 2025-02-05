from confluent_kafka.admin import AdminClient, NewTopic


def main():
    admin_client = AdminClient({"bootstrap.servers": "localhost:19092,localhost:29092"})
    topic = NewTopic("example_topic", num_partitions=1, replication_factor=1)
    admin_client.create_topics([topic])
    print("Topic example_topic created successfully.")


if __name__ == "__main__":
    main()
