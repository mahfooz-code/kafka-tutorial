tar -zxf kafka_2.11-0.9.0.1.tgz
mv kafka_2.11-0.9.0.1 /usr/local/kafka
mkdir /tmp/kafka-logs
export JAVA_HOME=/usr/java/jdk1.8.0_51
/usr/local/kafka/bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties
