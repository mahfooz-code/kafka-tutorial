import json as json
import time
from concurrent.futures import ThreadPoolExecutor
from time import sleep

import requests
from kafka import KafkaProducer


# Set up Kafka producer
def serializer(message):
    return json.dumps(message).encode("utf-8")


# Kafka Producer
producer = KafkaProducer(
    bootstrap_servers=["localhost:19092", "localhost:29092", "localhost:39092"],
    value_serializer=serializer,
)


def get_push_records(debug: bool = True):
    # Define function to flatten client records
    def flatten_record(server, client_obj):
        client_obj.pop("mac", None)
        client_obj.pop("deviceHash", None)
        client_obj.pop("lon", None)
        client_obj.pop("lat", None)
        client_obj.pop("ip4", None)
        if "ip" in client_obj:
            client_obj["client_ip"] = client_obj.pop("ip")
        client_obj["application"] = ip_to_name.get(server["ip"], "")
        client_obj["timestamp"] = int(time.time() * 1000)
        flat_record_data = json.dumps(
            {
                "timestamp": client_obj["timestamp"],
                "application": client_obj["application"],
                "client_ip": client_obj["client_ip"],
                "server_ip": server["ip"],
                "server_latlon": server["latlon"],
                **client_obj,
            }
        )
        return flat_record_data

    # Fetch nested JSON record from Dropbox link
    response = requests.get(
        url="https://www.dropbox.com/s/iwcpg1oo59i4yrn/exfoCustosTest%20%283%29.json?dl=1",
        timeout=60,
    )
    json_data = json.loads(response.content)

    # Extract servers and services arrays
    servers = json_data["servers"]
    services = json_data["services"]

    # Create dictionary mapping IP addresses to service names
    ip_to_name = {
        server["ip"]: service["name"]
        for service in services
        for server in service["servers"]
    }

    # Output flattened JSON record for each client record using threads
    with ThreadPoolExecutor(max_workers=16) as executor:
        future_results = []
        for server in servers:
            for client in server["clients"]:
                future = executor.submit(flatten_record, server, client)
                future_results.append(future)

        for future in future_results:
            flat_record = future.result()
            if debug:
                print(f"Sending record to kafka Stream: {json.loads(flat_record)}")
                data = json.loads(flat_record)
                print(data["timestamp"])

                producer.send("movies", flat_record)
                producer.flush()  # Wait for messages to be delivered


def main():
    while True:
        get_push_records()
        sleep(2)


if __name__ == "__main__":
    main()
