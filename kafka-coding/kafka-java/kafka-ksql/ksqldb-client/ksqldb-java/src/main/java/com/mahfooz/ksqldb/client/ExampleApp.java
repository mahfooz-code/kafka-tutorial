package com.mahfooz.ksqldb.client;

import com.mahfooz.ksqldb.client.async.RowSubscriber;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;

public class ExampleApp {

    public static String KSQLDB_SERVER_HOST = "mtmdevhdoped01";
    public static int KSQLDB_SERVER_HOST_PORT = 18088;

    public static void main(String[] args) {
        ClientOptions options = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT);
        Client client = Client.create(options);

        // Send requests with the client by following the other examples
        client.streamQuery("SELECT * FROM MY_STREAM EMIT CHANGES;")
                .thenAccept(streamedQueryResult -> {
                    System.out.println("Query has started. Query ID: " + streamedQueryResult.queryID());
                    RowSubscriber subscriber = new RowSubscriber();
                    streamedQueryResult.subscribe(subscriber);
                }).exceptionally(e -> {
            System.out.println("Request failed: " + e);
            return null;
        });
        // Terminate any open connections and close the client
        client.close();
    }
}