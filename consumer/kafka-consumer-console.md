#   kafka consumer console
    kafka-console-consumer \
        --bootstrap-server localhost:9092 \
        --topic users \
        --from-beginning


#   kafka console consumer more information
    kafka-console-consumer \
        --bootstrap-server localhost:9092 \
        --topic users \
        --property print.headers=true \
        --property print.timestamp=true \
        --property print.key=true \
        --property print.value=true \
        --from-beginning 
