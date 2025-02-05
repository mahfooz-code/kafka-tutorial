from confluent_kafka.schema_registry import SchemaRegistryClient

schema_registry_conf = {"url": "http://localhost:8081"}
schema_registry_client = SchemaRegistryClient(schema_registry_conf)

subjects = schema_registry_client.get_subjects()
for subject in subjects:
    schema = schema_registry_client.get_latest_version(subject)
    print(f"Subject: {subject}, Schema: {schema.schema.schema_str}")


schema = schema_registry_client.get_latest_version("user-value")
if schema is not None:
    print(schema.schema.schema_str)
else:
    print("Schema not found")
