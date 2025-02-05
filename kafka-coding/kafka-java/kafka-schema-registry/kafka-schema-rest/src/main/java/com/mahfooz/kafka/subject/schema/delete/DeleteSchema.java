/*

Deleting Version 1 of the Schema Registered Under Subject "Employee"

     curl -X DELETE mtmdevhdoped01.intl.vsnl.co.in:8081/subjects/Employee/versions/1

Deleting the Most Recently Registered Schema Under Subject "Kafka-value"

    curl -X DELETE http://mtmdevhdoped01:8081/subjects/Kafka-value/versions/latest

Deleting All Schema Versions Registered Under the Subject "Kafka-value"
    curl -X DELETE http://mtmdevhdoped01:8081/subjects/Kafka-value


curl -X DELETE http://localhost:8081/subjects/healthchecks-value/versions/latest

 */
package com.mahfooz.kafka.subject.schema.delete;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_BASE_URL;

public class DeleteSchema {

    public static void main(String[] args) {

        try {
            final OkHttpClient client = new OkHttpClient();

            //Delete VERSIONS 1 OF Employee
            Request request = new Request.Builder()
                    .delete()
                    .url(SCHEMA_BASE_URL+"/subjects/Employee/versions/1")
                    .build();

            String output = client.newCall(request).execute().body().string();
            System.out.println(output);

            //Delete all versions 1 OF Employee
            request = new Request.Builder()
                    .delete()
                    .url(SCHEMA_BASE_URL+"/subjects/transactions-value")
                    .build();

            output = client.newCall(request).execute().body().string();
            System.out.println(output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
