/*
Testing Compatibility of a Schema with the Latest Schema Under Subject “Kafka-value”

    curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    --data '{"schema": "{\"type\": \"string\"}"}' \
    http://mtmdevhdoped01:8081/compatibility/subjects/Kafka-value/versions/latest

 */
package com.mahfooz.kafka.subject.schema.compatibility;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_BASE_URL;
import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_CONTENT;

public class Compatibility {

    private final static String Employee_SCHEMA = "{\n" + " \"schema\": \"" + " {"
            + " \\\"namespace\\\": \\\"com.mahfooz.kafka.model\\\"," + " \\\"type\\\": \\\"record\\\","
            + " \\\"name\\\": \\\"Employee\\\"," + " \\\"fields\\\": ["
            + " {\\\"name\\\": \\\"fName\\\", \\\"type\\\": \\\"string\\\"},"
            + " {\\\"name\\\": \\\"lName\\\", \\\"type\\\": \\\"string\\\"},"
            + " {\\\"name\\\": \\\"age\\\",  \\\"type\\\": \\\"int\\\"},"
            + " {\\\"name\\\": \\\"phoneNumber\\\",  \\\"type\\\": \\\"string\\\"}" + " ]" + " }\"" + "}";

    public static void main(String[] args) {

        try {
            final OkHttpClient client = new OkHttpClient();

            // TEST Compatibility
            Request request = new Request.Builder().post(RequestBody.create(Employee_SCHEMA, SCHEMA_CONTENT))
                    .url(SCHEMA_BASE_URL + "/compatibility/subjects/Employee/versions/latest").build();

            String output = client.newCall(request).execute().body().string();
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
