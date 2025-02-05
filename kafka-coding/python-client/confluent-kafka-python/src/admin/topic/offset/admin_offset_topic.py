from confluent_kafka.admin import AdminClient, OffsetSpec
from confluent_kafka import IsolationLevel, TopicPartition, KafkaException


def fetch_offset(admin_client, topic):

    # Fetch metadata
    metadata = admin_client.list_topics(timeout=10)

    if topic in metadata.topics:
        partitions = metadata.topics[topic].partitions
        for partition_id in partitions:
            topic_partition = TopicPartition(topic, partition_id)
            # Begin and end offsets
            tp_begin = admin_client.list_offsets({topic_partition: OffsetSpec.latest()})
            tp_end = admin_client.list_offsets({topic_partition: OffsetSpec.earliest()})

            for partition, fut in tp_begin.items():
                try:
                    result = fut.result()
                    print(
                        f"""Topicname : {partition.topic}
                        Partition_Index : {partition.partition}
                        Offset : {result.offset}
                        Timestamp : {result.timestamp}"""
                    )
                except KafkaException as e:
                    print(
                        f"""Topicname : {partition.topic}
                        Partition_Index : {partition.partition}
                        Error : {e}"""
                    )

            for partition, fut in tp_end.items():
                try:
                    result = fut.result()
                    print(
                        f"""Topicname : {partition.topic}
                        Partition_Index : {partition.partition}
                        Offset : {result.offset}
                        Timestamp : {result.timestamp}"""
                    )
                except KafkaException as e:
                    print(
                        f"""Topicname : {partition.topic}
                        Partition_Index : {partition.partition}
                        Error : {e}"""
                    )

            # print(
            #     f"""Partition {partition_id} ->
            #     Begin Offset: {begin_offset},
            #     End Offset: {end_offset}"""
            # )
    else:
        print(f"Topic '{topic}' does not exist.")


def list_offsets(admin, args: list[str]):
    topic_partition_offsets = {}
    if len(args) == 0:
        raise ValueError(
            "Invalid number of arguments for list offsets, expected at least 1, got 0"
        )
    i = 1
    partition_i = 1
    isolation_level = IsolationLevel[args[0]]
    while i < len(args):
        if i + 3 > len(args):
            raise ValueError(
                f"Invalid number of arguments for list offsets, partition {partition_i}, expected 3,"
                + f" got {len(args) - i}"
            )
        topic = args[i]
        partition = int(args[i + 1])
        topic_partition = TopicPartition(topic, partition)

        if "EARLIEST" == args[i + 2]:
            offset_spec = OffsetSpec.earliest()

        elif "LATEST" == args[i + 2]:
            offset_spec = OffsetSpec.latest()

        elif "MAX_TIMESTAMP" == args[i + 2]:
            offset_spec = OffsetSpec.max_timestamp()

        elif "TIMESTAMP" == args[i + 2]:
            if i + 4 > len(args):
                raise ValueError(
                    f"Invalid number of arguments for list offsets, partition {partition_i}, expected 4"
                    + f", got {len(args) - i}"
                )
            offset_spec = OffsetSpec.for_timestamp(int(args[i + 3]))
            i += 1
        else:
            raise ValueError(
                "Invalid OffsetSpec, must be EARLIEST, LATEST, MAX_TIMESTAMP or TIMESTAMP"
            )
        topic_partition_offsets[topic_partition] = offset_spec
        i = i + 3
        partition_i += 1

    futmap = admin.list_offsets(
        topic_partition_offsets, isolation_level=isolation_level, request_timeout=30
    )
    for partition, fut in futmap.items():
        try:
            result = fut.result()
            print(
                "Topicname : {} Partition_Index : {} Offset : {} Timestamp : {}".format(
                    partition.topic,
                    partition.partition,
                    result.offset,
                    result.timestamp,
                )
            )
        except KafkaException as e:
            print(
                "Topicname : {} Partition_Index : {} Error : {}".format(
                    partition.topic, partition.partition, e
                )
            )


def main():

    # Configuration for the admin client
    admin_conf = {"bootstrap.servers": "localhost:19092,localhost:29092"}
    admin_client = AdminClient(admin_conf)

    # Topic name
    topic = "purchases"

    fetch_offset(admin_client, topic)


if __name__ == "__main__":
    main()
