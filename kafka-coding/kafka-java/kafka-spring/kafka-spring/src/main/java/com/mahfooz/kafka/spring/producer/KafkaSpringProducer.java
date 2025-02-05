package com.mahfooz.kafka.spring.producer;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaSpringProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private NewTopic topicName;

    public void sendMessage(String msg) {
        kafkaTemplate.send(topicName.name(), msg);
    }

    public static void main(String[] args) {

    }
}
