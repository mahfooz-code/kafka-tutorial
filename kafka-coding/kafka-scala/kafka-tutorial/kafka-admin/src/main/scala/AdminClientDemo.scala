import org.apache.kafka.clients.admin.{AdminClient, AdminClientConfig}

import java.util
import java.util.Properties

object AdminClientDemo {

  def main(args: Array[String]): Unit = {
    val bootstrapServer=args(0)
    val topicName=args(1)
    println(getPartitionFromTopic(topicName,bootstrapServer))
  }

  def getPartitionFromTopic(topic:String,bootstrapServer: String): Int = {
    val properties=new Properties()
    properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer)
    val admin=AdminClient.create(properties)
    val topicDetails=admin.describeTopics(util.Arrays.asList(topic))
    topicDetails.values().get(topic).get().partitions().size();
  }
}