package com.mahfooz.kafka.admin.topic.partition;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class DeleteRecordTopic {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "10.133.43.94:29092");
        AdminClient admin = AdminClient.create(props);

        Map<TopicPartition, OffsetSpec> requestOlderOffsets = new HashMap<>();

        Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> olderOffsets =
                admin.listOffsets(requestOlderOffsets).all().get();
        Map<TopicPartition, RecordsToDelete> recordsToDelete = new HashMap<>();
        for (Map.Entry<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo>  e:
                olderOffsets.entrySet())
            recordsToDelete.put(e.getKey(),
                    RecordsToDelete.beforeOffset(e.getValue().offset()));
        admin.deleteRecords(recordsToDelete).all().get();

        admin.close(Duration.ofSeconds(60));

    }

}
