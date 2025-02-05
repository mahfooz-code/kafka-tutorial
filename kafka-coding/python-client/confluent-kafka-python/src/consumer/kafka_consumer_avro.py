from confluent_kafka.avro import AvroConsumer

schema_registry_url = "http://localhost:8081"

consumer_conf = {
    "bootstrap.servers": "localhost:19092,localhost:29092",
    "group.id": "python-avro-consumer-group",
    "schema.registry.url": schema_registry_url,
    "auto.offset.reset": "earliest",
}

avro_consumer = AvroConsumer(consumer_conf)
avro_consumer.subscribe(["test-topic"])

try:
    while True:
        msg = avro_consumer.poll(1.0)
        if msg is None:
            continue
        if msg.error():
            print(f"Consumer error: {msg.error()}")
            continue

        print(f"Received key: {msg.key()}, value: {msg.value()}")

except KeyboardInterrupt:
    print("Consumer interrupted.")

finally:
    avro_consumer.close()
