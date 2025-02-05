from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.schema_registry_client import Schema

schema_registry_conf = {"url": "http://localhost:8081"}
schema_registry_client = SchemaRegistryClient(schema_registry_conf)

schema_str = """
{
  "type": "record",
  "name": "User",
  "fields": [
    {"name": "name", "type": "string"},
    {"name": "age", "type": "int"}
  ]
}
"""

subject = "user-value"
schema = Schema(schema_str, schema_type="AVRO")
schema_registry_client.register_schema(subject, schema)

subjects = schema_registry_client.get_subjects()
for subject in subjects:
    schema = schema_registry_client.get_latest_version(subject)
    print(f"Subject: {subject}, Schema: {schema.schema.schema_str}")
