from kafka import KafkaProducer

producer = KafkaProducer(
    bootstrap_servers="localhost:9093",
    security_protocol="SSL",
    ssl_cafile="/path/to/ca.pem",
    ssl_certfile="/path/to/cert.pem",
    ssl_keyfile="/path/to/key.pem",
)

producer.send("my-topic", b"Secure message")
producer.close()
