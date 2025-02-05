/*

Registering a New Version of a Schema Under the Subject "Kafka-key"

    curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \\\
    --data '{"schema": "{\\\"type\\\": \\\"string\\\"}"}' \\\
    http://mtmdevhdoped01:8081/subjects/Kafka-key/versions

Registering a New Version of a Schema Under the Subject "Kafka-value"

    curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \\\
      --data '{"schema": "{\\\"type\\\": \\\"string\\\"}"}' \\\
       http://mtmdevhdoped01:8081/subjects/Kafka-value/versions

Registering an Existing Schema to a New Subject Name

    curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \\\
    	--data "{\\\"schema\\\": $(curl -s http://mtmdevhdoped01:8081/subjects/Kafka1-value/versions/latest | jq '.schema')}" \\\
    	http://mtmdevhdoped01:8081/subjects/Kafka2-value/versions

Registering the Same Schema Under the Subject "Kafka-value"

    curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \\\
    	--data '{"schema": "{\\\"type\\\": \\\"string\\\"}"}' \\\
    	http://mtmdevhdoped01:8081/subjects/Kafka-value/versions

Checking if a Schema Is Registered Under Subject "Kafka-key"

	curl -X POST -H "Content-Type: application/vnd.schemaregistry.v1+json" \\\
		--data '{"schema": "{\\\"type\\\": \\\"string\\\"}"}' \\\
		http://mtmdevhdoped01:8081/subjects/Kafka-key

 */
package com.mahfooz.kafka.subject.schema.register;

import com.mahfooz.kafka.subject.prop.KafkaSchemaProp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

import java.io.IOException;

public class RegisterSchema {

	private final static String Employee_SCHEMA = "{\n" +
			" \"schema\": \"" +
			" {" +
			" \\\"namespace\\\": \\\"com.mahfooz.kafka.model\\\"," +
			" \\\"type\\\": \\\"record\\\"," +
			" \\\"name\\\": \\\"Employee\\\"," +
			" \\\"fields\\\": [" +
			" {\\\"name\\\": \\\"fName\\\", \\\"type\\\": \\\"string\\\"}," +
			" {\\\"name\\\": \\\"lName\\\", \\\"type\\\": \\\"string\\\"}," +
			" {\\\"name\\\": \\\"age\\\",  \\\"type\\\": \\\"int\\\"}," +
			" {\\\"name\\\": \\\"phoneNumber\\\",  \\\"type\\\": \\\"string\\\"}" +
			" ]" +
			" }\"" +
			"}";

	public static void main(String[] args) throws IOException {

		System.out.println("Schema = "+Employee_SCHEMA);
		final OkHttpClient client = new OkHttpClient();

		//POST A NEW SCHEMA
		Request request = new Request.Builder()
				.post(RequestBody.create(Employee_SCHEMA,KafkaSchemaProp.SCHEMA_CONTENT))
				.url(KafkaSchemaProp.SCHEMA_BASE_URL+"/subjects/Employee/versions")
				.build();

		String output = client.newCall(request).execute().body().string();
		System.out.println("Output: \\\t"+output);
	}
}
