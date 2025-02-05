from kafka import KafkaClient

if __name__ == "__main__":
    # Define your Kafka configuration
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:39092"

    # Create a Kafka client
    client = KafkaClient(bootstrap_servers=bootstrap_servers)

    # Fetch cluster metadata
    cluster_metadata = client.cluster

    # Print cluster metadata
    print(f"Cluster ID: {cluster_metadata}")
    print(f"Controller ID: {cluster_metadata.controller}")
    print("Brokers:")
    for broker in cluster_metadata.brokers():
        print(f"  Broker ID: {broker.nodeId}, Host: {broker.host}, Port: {broker.port}")
    print("Topics:")
    for topic in cluster_metadata.topics():
        print(
            f"""Topic: {topic},
            Partitions: {len(cluster_metadata.partitions_for_topic(topic))}"""
        )
