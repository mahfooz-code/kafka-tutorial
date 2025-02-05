package com.mahfooz.kafka.admin.spring;

import com.mahfooz.kafka.admin.spring.controller.ClusterController;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class KafkaSpringAppTest {

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Autowired
    private ClusterController controller;

}