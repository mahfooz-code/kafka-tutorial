from confluent_kafka import Producer


class CustomPartitioner:
    def __call__(self, key, partitions, msg):
        return int(key) % len(partitions)  # Assign partition based on integer key


def set_producer_configs():
    config_dict = {}
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    config_dict["partitioner"] = CustomPartitioner()
    return config_dict


def main():
    conf = set_producer_configs()
    producer = Producer(conf)

    for i in range(5):
        producer.produce(
            "partition_assignment", key=str(i), value=f"Custom Partitioned Message {i}"
        )
        print(f"Custom Partitioning: Message {i} sent to partition {i % 3}")

    producer.flush()


if __name__ == "__main__":
    main()
