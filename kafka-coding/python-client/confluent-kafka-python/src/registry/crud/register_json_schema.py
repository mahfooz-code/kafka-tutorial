from confluent_kafka.schema_registry import SchemaRegistryClient
from confluent_kafka.schema_registry.schema_registry_client import Schema

schema_registry_client = SchemaRegistryClient({"url": "http://localhost:8081"})

json_schema_str = """
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "title": "User",
  "type": "object",
  "properties": {
    "name": {"type": "string"},
    "age": {"type": "integer"}
  }
}
"""

subject = "user-json-value"
schema = Schema(json_schema_str, schema_type="JSON")
schema_registry_client.register_schema(subject, schema)
subjects = schema_registry_client.get_subjects()
for subject in subjects:
    schema = schema_registry_client.get_latest_version(subject)
    print(f"Subject: {subject}, Schema: {schema.schema.schema_str}")
