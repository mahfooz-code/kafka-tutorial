#Creating topic
  kafka-topics --zookeeper mtmdevhdoped01:2181/kafka \
    --create \
    --topic move-api-notification-test \
    --replication-factor 2 \
    --partitions 2 \
    --if-not-exists



#Adding Partitions
  kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster --alter --topic my-topic --partitions 16

#Deleting a Topic
#For deletion of a topic, we must "delete.topic.enable=true"
  kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster \
    --delete --topic my-topic


#Listing All Topics in a Cluster

  #GENERAL
  kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster --list

  #CDH
  kafka-topics --zookeeper mtmdevhdoped01:2181/kafka --list


#Describing Topic Details

  #GENERAL
  kafka-topics.sh --zookeeper zoo1.example.com:2181/kafka-cluster --describe

  #CDH
  kafka-topics --zookeeper mtmdevhdoped01:2181/kafka --describe --under-replicated-partitions

  #In order to find all topics that have configuration overrides, use the --topics-with-overrides argument
  kafka-topics --zookeeper mtmdevhdoped01:2181/kafka --describe --topics-with-overrides