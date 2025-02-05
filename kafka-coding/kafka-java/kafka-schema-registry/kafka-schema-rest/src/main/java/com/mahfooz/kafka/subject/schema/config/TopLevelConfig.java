/*
Getting the Top Level Config
    curl -X GET http://mtmdevhdoped01:8081/config

Updating Compatibility Requirements Globally
    curl -X PUT -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    --data '{"compatibility": "NONE"}' \
    http://mtmdevhdoped01:8081/config

 */
package com.mahfooz.kafka.subject.schema.config;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_BASE_URL;
import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_CONTENT;

public class TopLevelConfig {

    public static void main(String[] args) {
        try {
            final OkHttpClient client = new OkHttpClient();

            // TOP LEVEL CONFIG
            Request request = new Request.Builder()
                    .url(SCHEMA_BASE_URL+"/config")
                    .build();

            String output  = client.newCall(request).execute().body().string();
            System.out.println(output);


            // SET TOP LEVEL CONFIG
            // VALUES are none, backward, forward and full
            request = new Request.Builder()
                    .put(RequestBody.create( "{\"compatibility\": \"none\"}",SCHEMA_CONTENT))
                    .url(SCHEMA_BASE_URL+"/config")
                    .build();

            output = client.newCall(request).execute().body().string();
            System.out.println(output);


            // SET CONFIG FOR Employee
            // VALUES are none, backward, forward and full
            request = new Request.Builder()
                    .put(RequestBody.create( "{\"compatibility\": \"backward\"}",SCHEMA_CONTENT))
                    .url(SCHEMA_BASE_URL+"/config/Employee")
                    .build();

            output = client.newCall(request).execute().body().string();
            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
