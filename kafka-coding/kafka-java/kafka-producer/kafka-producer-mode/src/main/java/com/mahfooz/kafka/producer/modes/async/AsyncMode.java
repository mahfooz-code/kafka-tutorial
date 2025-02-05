/*

We call the send() method with a callback function, which gets triggered when it receives a response from 
the Kafka broker.

 */
package com.mahfooz.kafka.producer.modes.async;

import com.mahfooz.kafka.producer.util.ProducerConfigUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Mahfooz Alam
 */
public class AsyncMode {

    private static String KAFKA_CONFIG_FILE="D:/data/messaging/kafka/config/kafka-config.properties";

    public static void main(String[] args) throws IOException {

        String topicName="topic.mode";
        String message=" {\"InitiatorId\":\"TataMOVENL\",\"TransactionID\":\"AD25DD50-DEBE-41B7-921E-979EEB59232D\",\"eventName\":\"ProductPurchaseAddOn\",\"TransactionId\":\"AD25DD50-DEBE-41B7-921E-979EEB59232D\",\"resourceID\":\"7b9859a7-6573-eb11-a2cf-005056a660b3\",\"subResourceID\":\"5c921896-3959-4bc6-a324-52c5abd356b4\",\"result\":{\"resultCode\":200,\"details\":[{\"internCode\":\"200\"}]},\"customAttributes\":[{\"key\":\"StartDate\",\"value\":\"2021-02-20T11:24:10\"},{\"key\":\"ExpirationDate\",\"value\":\"2022-02-20T10:24:10Z\"}],\"frequency\":\"Recurring\",\"msisdn\":\"3197000266194\",\"iccid\":\"893107112031385007\",\"subscriptionType\":\"Eus\",\"addonName\":\"50 GB DATA LIMIT RECURRING FOR ONE YEAR (256/256-JLR)\",\"sourceId\":\"7D4F4C47-6C35-4076-9233-5A077B78FCE1\",\"tenancyID\":\"SECS_24048_00004\",\"eventId\":\"AD25DD50-DEBE-41B7-921E-979EEB59232D\",\"eventTimeStamp\":\"2021-02-20T10:23:04Z\",\"entityType\":\"Account\",\"entityId\":\"7b9859a7-6573-eb11-a2cf-005056a660b3\",\"eventClass\":\"info\"}";

        Properties kafkaProps = ProducerConfigUtil.getProducerProps(KAFKA_CONFIG_FILE);
        kafkaProps.put("key.serializer", StringSerializer.class.getName());
        kafkaProps.put("value.serializer", StringSerializer.class.getName());
        kafkaProps.setProperty(ProducerConfig.ACKS_CONFIG, "1");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(kafkaProps);) {
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName,  message);
            producer.send(record, new ProducerCallback());
        }

    }
}
