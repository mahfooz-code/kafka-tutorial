from kafka.admin import KafkaAdminClient
from kafka import KafkaConsumer, TopicPartition


def main():
    # Admin client to fetch metadata
    admin_client = KafkaAdminClient(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092"
    )
    topic_metadata = admin_client.describe_topics(["movies"])

    # Extract partitions
    topic = "movies"
    partitions = [p["partition"] for p in topic_metadata[0]["partitions"]]

    # KafkaConsumer to fetch offsets
    consumer = KafkaConsumer(
        bootstrap_servers="localhost:19092,localhost:29092,localhost:39092",
        group_id="offset_checker",
    )

    # Retrieve offsets
    for partition in partitions:
        tp = TopicPartition(topic, partition)
        beginning_offset = consumer.beginning_offsets([tp])[tp]
        end_offset = consumer.end_offsets([tp])[tp]
        print(
            f"""Partition {partition}:
            Earliest offset = {beginning_offset},
            Latest offset = {end_offset - 1}"""
        )
    consumer.close()


if __name__ == "__main__":
    main()
