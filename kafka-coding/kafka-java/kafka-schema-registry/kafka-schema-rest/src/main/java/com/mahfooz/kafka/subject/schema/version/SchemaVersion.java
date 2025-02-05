/*

Listing All Schema Versions Registered Under the Subject "Kafka-value"
    curl -X GET http://mtmdevhdoped01:8081/subjects/Kafka-value/versions

 */
package com.mahfooz.kafka.subject.schema.version;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

import static com.mahfooz.kafka.subject.prop.KafkaSchemaProp.SCHEMA_BASE_URL;

public class SchemaVersion {


	public static void main(String... args) throws IOException {

		final OkHttpClient client = new OkHttpClient();

		//SHOW ALL VERSIONS OF Employee
		Request request = new Request.Builder()
				.url(SCHEMA_BASE_URL+"/subjects/Employee/versions/")
				.build();

		String output = client.newCall(request).execute().body().string();
		System.out.println(output);

		//SHOW VERSION 2 OF Employee
		request = new Request.Builder()
				.url(SCHEMA_BASE_URL+"/subjects/Employee/versions/2")
				.build();

		output = client.newCall(request).execute().body().string();
		System.out.println(output);

		//SHOW THE SCHEMA WITH ID 3
		request = new Request.Builder()
				.url(SCHEMA_BASE_URL+"/schemas/ids/3")
				.build();

		output = client.newCall(request).execute().body().string();
		System.out.println(output);

		//SHOW THE LATEST VERSION OF Employee 2
		request = new Request.Builder()
				.url(SCHEMA_BASE_URL+"/subjects/Employee/versions/latest")
				.build();

		output = client.newCall(request).execute().body().string();
		System.out.println(output);
	}
}
