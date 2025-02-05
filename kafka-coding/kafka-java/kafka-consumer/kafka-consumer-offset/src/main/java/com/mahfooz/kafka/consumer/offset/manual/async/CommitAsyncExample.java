package com.mahfooz.kafka.consumer.offset.manual.async;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import java.time.Duration;
import java.util.*;

import com.mahfooz.kafka.consumer.util.ConsumerConfigUtil;

public class CommitAsyncExample {
    
  private static String TOPIC_NAME = "example-topic-2020-5-28c";
  private static KafkaConsumer<String, String> consumer;
  private static TopicPartition topicPartition;

  public static void main(String[] args) throws Exception {
      Properties consumerProps = ConsumerConfigUtil.getConsumerProps(false,0L);
      consumer = new KafkaConsumer<>(consumerProps);
      topicPartition = new TopicPartition(TOPIC_NAME, 0);
      consumer.assign(Collections.singleton(topicPartition));
      printOffsets("before consumer loop");
      sendMessages();
      startConsumer();
  }

  private static void startConsumer() {

      while (true) {
          ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(5));
          for (ConsumerRecord<String, String> record : records) {
              System.out.printf("consumed: key = %s, value = %s, partition id= %s, offset = %s%n",
                      record.key(), record.value(), record.partition(), record.offset());
              consumer.commitAsync(
                      Collections.singletonMap(topicPartition,  new OffsetAndMetadata(record.offset())),
                      (offsets, exception) -> {
                  System.out.printf("Callback, offset: %s, exception %s%n", offsets, exception);
              });
          }
          if (records.isEmpty()) {
              System.out.println("-- terminating consumer --");
              break;
          }
      }
      printOffsets("after consumer loop");
  }

  private static void printOffsets(String message) {
      
      OffsetAndMetadata offsetAndMetadata = consumer.committed(topicPartition);
      long position = consumer.position(topicPartition);
      System.out
              .printf("Offset info %s, Committed: %s, current position %s%n", message,
                      offsetAndMetadata == null ? null : offsetAndMetadata
                      .offset(), position);
  }

  private static void sendMessages() {
      Properties producerProps = ConsumerConfigUtil.getProducerProps();
      KafkaProducer<String, String> producer = new KafkaProducer<>(producerProps);
      for (int i = 0; i < 4; i++) {
          String value = "message-" + i;
          System.out.printf("Sending message topic: %s, value: %s%n", TOPIC_NAME, value);
          producer.send(new ProducerRecord<>(TOPIC_NAME, value));
      }
      producer.flush();
      producer.close();
  }
}