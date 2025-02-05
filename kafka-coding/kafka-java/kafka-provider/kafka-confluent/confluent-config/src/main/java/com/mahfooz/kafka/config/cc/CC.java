/*

1)  Navigate to the Control Center properties file (/etc/confluent-control-center/control-center-production.properties)

    bootstrap.servers=<hostname1:port1,hostname2:port2,hostname3:port3,...>
    # location for Control Center data
    confluent.controlcenter.data.dir=/var/lib/confluent/control-center
    # the Confluent license
    confluent.license=<your-confluent-license>
    # ZooKeeper connection string with host and port of a ZooKeeper servers
    zookeeper.connect=<hostname1:port1,hostname2:port2,hostname3:port3,...>

2)  Navigate to the Kafka server configuration file (/etc/kafka/server.properties) and enable Confluent Metrics Reporter.

    metric.reporters=io.confluent.metrics.reporter.ConfluentMetricsReporter
    confluent.metrics.reporter.bootstrap.servers=localhost:9092
    confluent.metrics.reporter.topic.replicas=1

3)  Add these lines to the Kafka Connect properties file (/etc/kafka/connect-distributed.properties) to add support for the interceptors.

    consumer.interceptor.classes=io.confluent.monitoring.clients.interceptor.MonitoringConsumerInterceptor
    producer.interceptor.classes=io.confluent.monitoring.clients.interceptor.MonitoringProducerInterceptor

 */
package com.mahfooz.kafka.config.cc;

public class CC {
}
