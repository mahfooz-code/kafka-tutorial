from typing import List

from kafka.admin import KafkaAdminClient, NewTopic


def create_topics(
    admin_client: KafkaAdminClient,
    topic_names: List[str],
    num_of_partitions: int = 1,
    replication_factor: int = 1,
):
    existing_topic_list = admin_client.list_topics()
    topic_list = []

    for topic in topic_names:
        if topic not in existing_topic_list:
            print(f"Topic '{topic}' added")
            topic_list.append(
                NewTopic(
                    name=topic,
                    num_partitions=num_of_partitions,
                    replication_factor=replication_factor,
                )
            )
        else:
            print(f"Topic '{topic}' already exists")

    try:
        if topic_list:
            admin_client.create_topics(new_topics=topic_list, validate_only=False)
            print("Topics created successfully")
        else:
            print("Topics already exist")
    except Exception as e:
        print(e)


def list_topic(admin_client):
    existing_topic_list = admin_client.list_topics()
    print(f"topic list: {existing_topic_list}")


def main():
    # Set your Kafka broker address (bootstrap servers)
    bootstrap_servers = "localhost:19092,localhost:29092,localhost:39092"
    admin_client = KafkaAdminClient(bootstrap_servers=bootstrap_servers)

    # Define the topic names you want to create
    topic_names = ["movies"]
    create_topics(admin_client, topic_names, 3, 3)

    list_topic(admin_client)


if __name__ == "__main__":
    main()
