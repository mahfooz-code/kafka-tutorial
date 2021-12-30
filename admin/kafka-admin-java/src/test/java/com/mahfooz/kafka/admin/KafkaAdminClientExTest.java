package com.mahfooz.kafka.admin;

import com.mahfooz.kafka.admin.topic.creator.TopicCreator;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterConfigsResult;
import org.apache.kafka.clients.admin.MockAdminClient;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.concurrent.ExecutionException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class KafkaAdminClientExTest {

    private AdminClient admin;

    @BeforeEach
    public void setUp() {

        Node broker = new Node(0,"localhost",9092);
        this.admin = spy(new MockAdminClient(Collections.singletonList(broker),
                broker));

        // without this, the tests will throw
        // `java.lang.UnsupportedOperationException: Not implemented yet`
        AlterConfigsResult emptyResult = mock(AlterConfigsResult.class);
        doReturn(KafkaFuture.completedFuture(null)).when(emptyResult).all();
        doReturn(emptyResult).when(admin).incrementalAlterConfigs(any());
    }

    @Test
    public void testCreateTestTopic()
            throws ExecutionException, InterruptedException {
        TopicCreator tc = new TopicCreator(admin);
        tc.maybeCreateTopic("test.is.a.test.topic");
        verify(admin, times(1)).createTopics(any());
    }

    @Test
    public void testNotTopic() throws ExecutionException, InterruptedException {
        TopicCreator tc = new TopicCreator(admin);
        tc.maybeCreateTopic("not.a.test");
        verify(admin, never()).createTopics(any());
    }

}