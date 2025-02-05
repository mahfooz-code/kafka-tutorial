from confluent_kafka import Producer


def set_producer_configs():
    config_dict = {}
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    return config_dict


def main():
    conf = set_producer_configs()
    producer = Producer(conf)

    # Producing messages with a key (Kafka determines partition)
    for i in range(5):
        partition = i % 3
        value = f"Message {i}"
        producer.produce("partition_assignment", partition=partition, value=value)
        print(f"Produced {value} with partition {partition}")

    producer.flush()


if __name__ == "__main__":
    main()
