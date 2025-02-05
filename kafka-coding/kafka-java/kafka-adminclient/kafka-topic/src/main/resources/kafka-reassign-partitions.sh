#GENERAL
kafka-reassign-partitions.sh --zookeeper \
    zoo1.example.com:2181/kafka-cluster --generate \
    --topics-to-move-json-file topics.json --broker-list 0,1


#CDH
kafka-reassign-partitions.sh --zookeeper \
    mtmdevhdoped01:2181/kafka --generate \
    --topics-to-move-json-file topics.json --broker-list 0,1


kafka-reassign-partitions.sh --zookeeper \
    zoo1.example.com:2181/kafka-cluster --execute \
    --reassignment-json-file reassign.json

kafka-reassign-partitions.sh --zookeeper \
    zoo1.example.com:2181/kafka-cluster --verify \
    --reassignment-json-file reassign.json

