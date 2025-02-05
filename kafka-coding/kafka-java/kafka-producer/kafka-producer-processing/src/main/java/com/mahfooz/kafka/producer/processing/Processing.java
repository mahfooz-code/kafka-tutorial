/*
Specifying a partition in the ProducerRecord will send the data to that partition by the partitioner. Otherwise, it will find a partition based on the key specified in the ProducerRecord.

Once a partition is chosen for the data, the producer will know which partition of the topic the data should go.

The partitioner then adds the record to a batch of records that will be sent to a specific partition of a topic.

Writing Message and acknowledging
Using a separate thread, the Producer will send each record from the batch of records to the appropriate Kafka Broker.

If the record is completely written to the broker, it will respond back to the Producer with a RecordMetadata object. This object contains the topic, partition, and the offset of the record within the partition.

If the broker fails to write to the partition log, it will send back an error.

When the producer receives the error, it will try resending the record.

*/
public class Processing {
    
}
