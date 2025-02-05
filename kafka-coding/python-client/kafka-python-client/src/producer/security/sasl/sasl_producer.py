from kafka import KafkaProducer

producer = KafkaProducer(
    bootstrap_servers="localhost:9092",
    security_protocol="SASL_PLAINTEXT",
    sasl_mechanism="SCRAM-SHA-256",
    sasl_plain_username="my-username",
    sasl_plain_password="my-password",
)

producer.send("my-topic", b"SASL authenticated message")
producer.close()
