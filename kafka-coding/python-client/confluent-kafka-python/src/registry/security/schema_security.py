from confluent_kafka.schema_registry import SchemaRegistryClient

schema_registry_conf = {
    "url": "http://localhost:8081",
    "basic.auth.user.info": "user:password",
}
schema_registry_client = SchemaRegistryClient(schema_registry_conf)
