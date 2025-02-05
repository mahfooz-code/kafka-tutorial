# The kafka-consumer-groups.sh tool can be used to list and describe both types of groups.

# When working with older consumer groups, you will access the Kafka cluster by specifying the --zookeeper command-line
# parameter for the tool.

# For new consumer groups, you will need to use the --bootstrap-server parameter with the hostname and port number of
# the Kafka broker to connect to instead.

#List and Describe Groups
#GENERAL
kafka-consumer-groups.sh --zookeeper zoo1.example.com:2181/kafka-cluster --list
kafka-consumer-groups.sh --zookeeper zoo1.example.com:2181/kafka-cluster --describe --group testgroup

#CDH
#kafka-consumer-groups --zookeeper mtmdevhdoped01:2181/kafka --list -- Not working
kafka-consumer-groups --bootstrap-server mtmdevhdoped01:9092 --list

#Describe the topic

#CDH
kafka-consumer-groups --bootstrap-server mtmdevhdoped01:9092 --describe --group flume-mmxkafka

#Delete the group

  #GENERAL
  kafka-consumer-groups.sh --zookeeper zoo1.example.com:2181/kafka-cluster --delete --group testgroup

  #CDH
  kafka-consumer-groups.sh --bootstrap-server mtmdevhdoped01:9092 --delete --group testgroup