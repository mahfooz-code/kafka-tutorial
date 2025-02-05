/*

Recommended
    KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

    while (true) {
      ConsumerRecords<String, String> records = consumer.poll(100);
    }

Not Recommended
    while (true) {
      KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
      ConsumerRecords<String, String> records = consumer.poll(100);
    }

Similarly, it is recommended that you use one consumer and/or producer object per thread.


In addition, Cloudera recommends to set and use a fixed client ID for producers and consumers when they are connecting to the brokers. If this is not done, Kafka will assign a new client id every time a new connection is established, which can severely increase resource utilization (memory) on the broker side.

Example: poll() gets KafkaException due to session timeout
while (true) {
  KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
  ConsumerRecords<String, String> records = consumer.poll(100);
  // the call below should return quickly in all cases
  myDataProcess.doStuff(records);
}


Catch all exceptions when polling
Java
C＃
From the poll() Javadoc page, you can see that the poll() method throws several exceptions. If the catch statements are not complete, then any uncaught exception will end up in the finally statement calling KafkaConsumer#close(). This will not be the desired behavior in many cases.

while (true) {
  try {
    ConsumerRecords<String, String> records = consumer.poll(100);
  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    consumer.close();
  }


 */
package com.mahfooz.kafka.consumer.practices;

public class BestPractices {
}
