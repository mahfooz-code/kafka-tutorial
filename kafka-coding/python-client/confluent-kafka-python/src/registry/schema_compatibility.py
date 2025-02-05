from confluent_kafka.schema_registry import SchemaRegistryClient

schema_registry_client = SchemaRegistryClient({"url": "http://localhost:8081"})
compatibility = schema_registry_client.get_compatibility("avro-topic-key")
print(f"Schema Compatibility: {compatibility}")
