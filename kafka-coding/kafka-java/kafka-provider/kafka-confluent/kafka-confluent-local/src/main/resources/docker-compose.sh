docker run -d \
    --name=ksqldb-server \
    --net=host \
    -p 38088:8088 \
    -e KSQL_BOOTSTRAP_SERVERS="mtmdevhdoped01:39092" \
    -e KSQL_KSQL_LOGGING_PROCESSING_STREAM_AUTO_CREATE=true \
    -e KSQL_KSQL_LOGGING_PROCESSING_TOPIC_AUTO_CREATE=true \
    -e KSQL_KSQL_CONNECT_WORKER_CONFIG="/connect/connect.properties" \
    -e KSQL_CONNECT_GROUP_ID="ksql-connect-cluster" \
    -e KSQL_CONNECT_BOOTSTRAP_SERVERS="mtmdevhdoped01:39092" \
    -e KSQL_CONNECT_KEY_CONVERTER="org.apache.kafka.connect.storage.StringConverter" \
    -e KSQL_CONNECT_VALUE_CONVERTER="org.apache.kafka.connect.json.JsonConverter" \
    -e KSQL_CONNECT_VALUE_CONVERTER_SCHEMAS_ENABLE=false \
    -e KSQL_CONNECT_CONFIG_STORAGE_TOPIC="ksql-connect-configs" \
    -e KSQL_CONNECT_OFFSET_STORAGE_TOPIC="ksql-connect-offsets" \
    -e KSQL_CONNECT_STATUS_STORAGE_TOPIC="ksql-connect-statuses" \
    -e KSQL_CONNECT_CONFIG_STORAGE_REPLICATION_FACTOR=1 \
    -e KSQL_CONNECT_OFFSET_STORAGE_REPLICATION_FACTOR=1 \
    -e KSQL_CONNECT_STATUS_STORAGE_REPLICATION_FACTOR=1 \
    -e KSQL_CONNECT_PLUGIN_PATH=/usr/share/kafka/plugins \
    -e KSQL_LISTENERS="http://0.0.0.0:8088" \
    -e KSQL_KSQL_SERVICE_ID="ksql_service" \
    -v /app/hadoop_users/Mahfooz/kafka/kafka-connect/jars:/usr/share/kafka/plugins/debezium \
    -v /app/hadoop_users/Mahfooz/kafka/kafka-connect/confluentinc-kafka-connect-hdfs-5.4.1:/usr/share/kafka/plugins/hdfs-sink \
    confluentinc/cp-ksqldb-server
