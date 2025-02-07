import java.util.Properties
import org.apache.kafka.clients.producer._

object Producer {

  def main(args: Array[String]): Unit = {
    writeToKafka("quick-start")
  }

  def writeToKafka(topic: String): Unit = {
    val props = new Properties()
    props.put("bootstrap.servers", "localhost:19092,localhost:29092")
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")
    val producer = new KafkaProducer[String, String](props)
    val record = new ProducerRecord[String, String](topic, "key", "value")
    producer.send(record)
    producer.close()
  }
}
