/*

Listing All Subjects

    curl -X GET http://mtmdevhdoped01:8081/subjects/

Fetching a Schema by Globally Unique ID 1

    curl -X GET http://mtmdevhdoped01:8081/schemas/ids/1

Fetch Version 1 of the Schema Registered Under Subject "Kafka-value"

    curl -X GET http://mtmdevhdoped01:8081/subjects/Kafka-value/versions/1


# Register a new version of a schema under the subject "Kafka-key"

curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    --data '{"schema": "{\"type\": \"string\"}"}' \
    http://localhost:8082/subjects/Kafka-key/versions

# Register a new version of a schema under the subject "Kafka-value"
$ curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \
    --data '{"schema": "{\"type\": \"string\"}"}' \
     http://localhost:8081/subjects/Kafka-value/versions
  {"id":1}


# List all subjects
$ curl -X GET http://localhost:8082/subjects
  ["Kafka-value","Kafka-key"]

 */
package com.mahfooz.kafka.subject.schema.list;

import com.mahfooz.kafka.subject.prop.KafkaSchemaProp;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

public class ListSchema {

	public static void main(String[] args) throws IOException {

		final OkHttpClient client = new OkHttpClient();

		//LIST ALL SCHEMAS
		Request request = new Request.Builder()
				.url(KafkaSchemaProp.SCHEMA_BASE_URL+"/subjects")
				.build();

		String output = client.newCall(request).execute().body().string();
		System.out.println(output);
	}
}
