package com.mahfooz.kafka.rest;

import io.confluent.rest.EmbeddedServerTestHarness;
import io.confluent.rest.RestConfigException;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HelloWorldResourceTest extends EmbeddedServerTestHarness<HelloWorldRestConfig, HelloWorldApplication> {

    private final static String mediatype = "application/vnd.hello.v1+json";

    public HelloWorldResourceTest() throws RestConfigException {
        // We need to specify which resources we want available, i.e. the ones we need to test. If we need
        // access to the server Configuration, as HelloWorldResource does, a default config is available
        // in the 'config' field.
        addResource(new HelloWorldResource(config));
    }

    @Test
    public void testHello() {
        String acceptHeader = mediatype;
        Response response = request("/hello", acceptHeader).get();
        // The response should indicate success and have the expected content type
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(mediatype, response.getMediaType().toString());

        // We should also be able to parse it as the expected output format
        final HelloWorldResource.HelloResponse message = response.readEntity(HelloWorldResource.HelloResponse.class);
        // And it should contain the expected message
        assertEquals("Hello, World!", message.getMessage());
    }
}