package com.mahfooz.kafka.stream.consumer.consumer

  import java.io.FileReader
  import java.time.Duration
  import java.util.{Collections, Properties}
  import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
  import com.mahfooz.kafka.stream.model.RecordJSON
  import org.apache.kafka.clients.consumer.{ConsumerConfig, KafkaConsumer}

  import scala.collection.JavaConversions._

  object Consumer extends App {
    val configFileName = args(0)
    val topicName = args(1)
    val props = buildProperties(configFileName)
    val consumer = new KafkaConsumer[String, JsonNode](props)
    val MAPPER = new ObjectMapper
    consumer.subscribe(Collections.singletonList(topicName))
    var total_count = 0L
    while(true) {
      println("Polling")
      val records = consumer.poll(Duration.ofSeconds(1))
      for (record <- records) {
        val key = record.key()
        val value = record.value()
        val countRecord = MAPPER.treeToValue(value, classOf[RecordJSON])
        total_count += countRecord.getCount
        println(s"Consumed record with key $key and value $value, and updated total count to $total_count")
      }
    }
    consumer.close()

    def buildProperties(configFileName: String): Properties = {
      val properties = new Properties()
      properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer")
      properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.connect.json.JsonDeserializer")
      properties.put(ConsumerConfig.GROUP_ID_CONFIG, "scala_example_group")
      properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest")
      properties.load(new FileReader(configFileName))
      properties
    }

}
