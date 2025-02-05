# kafka-python

Python client for the Apache Kafka distributed stream processing system.

kafka-python is designed to function much like the official java client, with a sprinkling of pythonic interfaces (e.g., consumer iterators).

## Features

### **Key Features**

- **Producer and Consumer APIs:** Publish and retrieve messages from Kafka topics.
- **High Performance :** Leverages Kafka's native protocol for efficient communication.

### Support for Kafka Features

- Offsets and partitioning
- Consumer groups
- Message batching
- TLS and SASL authentication

### **Ease of Use**

Pythonic interfaces for working with Kafka.

## KafkaConsumer

[`KafkaConsumer`](https://kafka-python.readthedocs.io/en/master/apidoc/KafkaConsumer.html#kafka.KafkaConsumer "kafka.KafkaConsumer") is a high-level message consumer, intended to operate as similarly as possible to the official java client.

Full support for coordinated consumer groups requires use of kafka brokers that support the Group APIs: kafka v0.9+.
