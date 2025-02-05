from kafka import KafkaAdminClient

if __name__ == "__main__":
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:29092"

    # Create a Kafka consumer
    admin_client = KafkaAdminClient(bootstrap_servers=bootstrap_servers)

    topic_list = admin_client.list_topics()
    print(topic_list)

    for topic in topic_list:
        topic_details = admin_client.describe_topics([topic])
        print(topic_details)
