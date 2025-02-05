import time

from confluent_kafka import Producer
from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.json_schema import JSONSerializer
from confluent_kafka.serialization import MessageField, SerializationContext

from models import Temperature

schema_str = """{
    "$schema": "https://json-schema.org/draft/2020-12/schema",
    "title": "Temperature",
    "description": "Temperature sensor reading",
    "type": "object",
    "properties": {
      "city": {
        "description": "City name",
        "type": "string"
      },
      "reading": {
        "description": "Current temperature reading",
        "type": "number"
      },
      "unit": {
        "description": "Temperature unit (C/F)",
        "type": "string"
      },
      "timestamp": {
        "description": "Time of reading in ms since epoch",
        "type": "number"
      }
    }
  }"""


def temp_to_dict(temp, ctx):
    return {
        "city": temp.city,
        "reading": temp.reading,
        "unit": temp.unit,
        "timestamp": temp.timestamp,
    }


def delivery_report(err, event):
    if err is not None:
        print(f"Delivery failed on reading for {event.key().decode('utf8')}: {err}")
    else:
        print(
            f"Temp reading for {event.key().decode('utf8')} produced to {event.topic()}"
        )


def set_producer_configs():
    config_dict = {}
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    return config_dict


def main():
    config = set_producer_configs()
    data = [
        Temperature("London", 12, "C", round(time.time() * 1000)),
        Temperature("Chicago", 63, "F", round(time.time() * 1000)),
        Temperature("Berlin", 14, "C", round(time.time() * 1000)),
        Temperature("Madrid", 18, "C", round(time.time() * 1000)),
        Temperature("Phoenix", 78, "F", round(time.time() * 1000)),
    ]

    topic = "temp_readings"
    schema_registry_client = SchemaRegistryClient({"url": "http://localhost:8081"})

    json_serializer = JSONSerializer(schema_str, schema_registry_client, temp_to_dict)

    producer = Producer(config)
    for temp in data:
        producer.produce(
            topic=topic,
            key=str(temp.city),
            value=json_serializer(
                temp, SerializationContext(topic, MessageField.VALUE)
            ),
            on_delivery=delivery_report,
        )

    producer.flush()


if __name__ == "__main__":
    main()
