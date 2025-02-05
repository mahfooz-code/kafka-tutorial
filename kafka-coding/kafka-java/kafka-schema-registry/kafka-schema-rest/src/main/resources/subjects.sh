# Register a new version of a schema under the subject "Kafka-key"

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"schema": "{\"type\": \"string\"}"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-key/versions


# Register a new version of a schema under the subject "Kafka-value"
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"schema": "{\"type\": \"string\"}"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value/versions

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{ "schema": "{ \"name\": \"HealthCheck\", \"namespace\": \"kioto.avro\", \"type\": \"record\",\"fields\": [ { \"name\": \"event\", \"type\": \"string\" }, { \"name\": \"factory\", \"type\": \"string\" },{ \"name\": \"serialNumber\", \"type\": \"string\" }, { \"name\": \"type\", \"type\": \"string\" }, { \"name\": \"status\", \"type\": \"string\"}, { \"name\": \"lastStartedAt\", \"type\": \"long\", \"logicalType\":  \"timestamp-millis\"}, { \"name\": \"temperature\", \"type\": \"float\" }, { \"name\": \"ipAddress\",  \"type\": \"string\" } ]} " }' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/healthchecks-avro-value/versions

#Registering a new version of a schema under a – key subject
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json"\
  --data 'our escaped avro data' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/healthchecks-avro-key/versions


curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json"\
  --data "{\"schema\": $(curl -s http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/healthchecks-value1/versions/latest | jq '.schema')}" \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/healthchecks-value2/versions


# List all subjects
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects

# List all schema versions registered under the subject "Kafka-value"
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value/versions

# Fetch a schema by globally unique id 1
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/schemas/ids/1

# Fetch version 1 of the schema registered under subject "Kafka-value"
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value/versions/1

# Fetch the most recently registered schema under subject "Kafka-value"
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value/versions/latest

# Delete version 3 of the schema registered under subject "Kafka-value"
curl -X DELETE http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value/versions/3


# Delete all versions of the schema registered under subject "Kafka-value"
curl -X DELETE http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-value


# Check whether a schema has been registered under subject "Kafka-key"
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"schema": "{\"type\": \"string\"}"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Kafka-key

# Test compatibility of a schema with the latest schema under subject "Kafka-value"
curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"schema": "{\"type\": \"string\"}"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/compatibility/subjects/Kafka-value/versions/latest

# Get top level config
curl -X GET http://mtmdevhdoped01.intl.vsnl.co.in:8081/config

# Update compatibility requirements globally
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"compatibility": "NONE"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/config


# Update compatibility requirements under the subject "Kafka-value"
curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" \
  --data '{"compatibility": "BACKWARD"}' \
  http://mtmdevhdoped01.intl.vsnl.co.in:8081/config/Kafka-value
