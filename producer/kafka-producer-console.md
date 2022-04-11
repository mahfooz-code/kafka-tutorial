#   In case of docker
    
    
    
#   Producing the message with key and value using kafka console producer
    kafka-console-producer \
        --bootstrap-server localhost:9092 \
        --property key.separator=, \
        --property parse.key=true \
        --topic users


#   Kafka console producer
    kafka-console-producer \
        --bootstrap-server localhost:9092 \
        --topic users
