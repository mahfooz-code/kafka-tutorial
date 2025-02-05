from confluent_kafka import Consumer


def set_consumer_configs():
    config_dict = {}
    config_dict["group.id"] = "consumer_group"
    config_dict["auto.offset.reset"] = "earliest"
    config_dict["enable.auto.commit"] = True
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    return config_dict


def assignment_callback(consumer, partitions):
    for p in partitions:
        print(f"Assigned to {p.topic}, partition {p.partition}")


if __name__ == "__main__":
    config = set_consumer_configs()
    consumer = Consumer(config)
    consumer.subscribe(["partition_assignment"], on_assign=assignment_callback)

    try:
        while True:
            msg = consumer.poll(1.0)
            if msg is None:
                print("Waiting...")
            elif msg.error():
                print(f"ERROR: {msg.error()}")
            else:
                print(
                    f"""Consumed event from topic {msg.topic()}:
                    key = {msg.key().decode("utf-8"):12}
                    value = {msg.value().decode("utf-8"):12}"""
                )
    except KeyboardInterrupt:
        print("Canceled by user.")
    finally:
        consumer.close()
