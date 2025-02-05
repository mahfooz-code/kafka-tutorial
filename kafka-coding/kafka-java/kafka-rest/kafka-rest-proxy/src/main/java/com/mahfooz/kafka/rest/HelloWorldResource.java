package com.mahfooz.kafka.rest;import com.fasterxml.jackson.annotation.JsonProperty;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import io.confluent.rest.annotations.PerformanceMetric;

@Path("/hello")
@Produces("application/vnd.hello.v1+json")
public class HelloWorldResource {
    HelloWorldRestConfig config;

    public HelloWorldResource(HelloWorldRestConfig config) {
        this.config = config;
    }


    /**
     * A simple response entity with validation constraints.
     */
    public static class HelloResponse {
        @NotEmpty
        private String message;

        public HelloResponse() { /* Jackson deserialization */ }

        public HelloResponse(String message) {
            this.message = message;
        }

        @JsonProperty
        public String getMessage() {
            return message;
        }
    }

    @GET
    @PerformanceMetric("hello-with-name")
    public HelloResponse hello(@QueryParam("name") String name) {
        // Use a configuration setting to control the message that's written. The name is extracted from
        // the query parameter "name", or defaults to "World". You can test this API with curl:
        // curl http://localhost:8080/hello
        //   -> {"message":"Hello, World!"}
        // curl http://localhost:8080/hello?name=Bob
        //   -> {"message":"Hello, Bob!"}
        return new HelloResponse(
                String.format(config.getString(HelloWorldRestConfig.GREETING_CONFIG),
                        (name == null ? "World" : name)));
    }
}
