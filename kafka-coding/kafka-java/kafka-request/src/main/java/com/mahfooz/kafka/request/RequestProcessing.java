/*

In Kafka, the client initiates a connection and sends requests. 
The requests could be a fetch request from consumer or a write request from the producer. 
The broker processes the request in the order; they are received. 
The broker then responds back to the client preserving the order of the message they store.

A client sends requests to the following:

    partition leaders
    partition replicas
    controller

Kafka uses a binary protocol (TCP) that specifies a format and defines how a broker should respond:
    when the request is processed successfully.
    when the broker meets with an error while processing the request.
c
 */

package com.mahfooz.kafka.request;

/**
 *
 * @author Mahfooz Alam
 */
public class RequestProcessing {
    public static void main(String[] args) {

    }
}
