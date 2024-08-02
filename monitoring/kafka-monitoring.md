# Kafka monitoring

## kafkadrop

    docker run -d --rm -p 9000:9000 \
    -e KAFKA_BROKERCONNECT=<host:port,host:port> \
    -e JVM_OPTS="-Xms32M -Xmx64M" \
    -e SERVER_SERVLET_CONTEXTPATH="/" \
    obsidiandynamics/kafdrop

## Confluent Control Centre

## Kafka Topics UI

    docker run --rm -it -p 8000:8000 \
        -e "KAFKA_REST_PROXY_URL=https://kafka-rest-proxy-host:port" \
        -e "PROXY=true" \
        landoop/kafka-topics-ui

## Datadog Kafka Dashboard
  