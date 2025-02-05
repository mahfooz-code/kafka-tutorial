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
        key = str(i)  # Key determines partition
        value = f"Message {i}"
        producer.produce("partition_assignment", value=value)
        print(f"Produced {value} with key {key}")

    producer.flush()


if __name__ == "__main__":
    main()
