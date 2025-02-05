docker-compose up -d --build

docker run -v $PWD/confluent-hub-components:/share/confluent-hub-components confluentinc/ksqldb-server:0.8.1 confluent-hub install --no-prompt confluentinc/kafka-connect-jdbc:5.5.0

docker exec -it ksqldb-server ksql http://mtmdevhdoped01:38088

