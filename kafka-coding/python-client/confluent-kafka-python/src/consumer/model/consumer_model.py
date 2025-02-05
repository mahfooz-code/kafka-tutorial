from confluent_kafka import Consumer
from confluent_kafka.serialization import SerializationContext, MessageField
from confluent_kafka.schema_registry.json_schema import JSONDeserializer

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


def dict_to_temp(dict, ctx):
    return Temperature(dict["city"], dict["reading"], dict["unit"], dict["timestamp"])


def set_consumer_configs():
    config_dict = {}
    config_dict["group.id"] = "temp_group"
    config_dict["auto.offset.reset"] = "earliest"
    config_dict["bootstrap.servers"] = "localhost:19092,localhost:29092,localhost:39092"
    return config_dict


def main():
    config = set_consumer_configs()
    topic = "temp_readings"

    json_deserializer = JSONDeserializer(schema_str, from_dict=dict_to_temp)

    consumer = Consumer(config)
    consumer.subscribe([topic])
    while True:
        try:
            event = consumer.poll(1.0)
            if event is None:
                continue
            temp = json_deserializer(event.value(), SerializationContext(topic, MessageField.VALUE))
            if temp is not None:
                print(f"Latest temp in {temp.city} is {temp.reading} {temp.unit}.")

        except KeyboardInterrupt:
            break

    consumer.close()


if __name__ == "__main__":
    main()
