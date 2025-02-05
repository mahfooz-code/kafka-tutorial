/*

1)  Installing Java

    export JAVA_HOME=/usr/java/jdk1.8.0_51

2)  Installing Zookeeper

    tar -zxf zookeeper-3.4.6.tar.gz
    mv zookeeper-3.4.6 /usr/local/zookeeper
    mkdir -p /var/lib/zookeeper

    cat > /usr/local/zookeeper/conf/zoo.cfg << EOF
    tickTime=2000
    dataDir=/var/lib/zookeeper
    clientPort=2181
    EOF

    tickTime=2000
    dataDir=/var/lib/zookeeper
    clientPort=2181
    initLimit=20
    syncLimit=5
    server.1=zoo1.example.com:2888:3888
    server.2=zoo2.example.com:2888:3888
    server.3=zoo3.example.com:2888:3888

    export JAVA_HOME=/usr/java/jdk1.8.0_51
    /usr/local/zookeeper/bin/zkServer.sh start


3)  Installing a Kafka Broker

    tar -zxf kafka_2.12-0.10.2.1.tgz
    mv kafka_2.12-0.10.2.1 /usr/local/kafka
    mkdir /tmp/kafka-logs
    export JAVA_HOME=/usr/java/jdk1.8.0_51

    /usr/local/kafka/bin/kafka-server-start.sh -daemon
    /usr/local/kafka/config/server.properties

4)

vi ~/.bashrc
export KAFKA_HOME=/kafka/kafka_2.10-0.8.2.1
export PATH=$PATH:$KAFKA_HOME/bin

*/

package com.mahfooz.kafka.admin.setup;

/**
 *
 * @author Mahfooz Alam
 */
public class Setup {
    public static void main(String [] args ) {
    
    }
}
