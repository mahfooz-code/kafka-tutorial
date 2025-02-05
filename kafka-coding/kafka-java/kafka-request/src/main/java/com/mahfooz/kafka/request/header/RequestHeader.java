/*
All the request headers should have:

    Request Type - This is the API Key.
    Request Version - enable brokers to handle clients independent of their versions and respond accordingly.
    Correlation ID - This is a number which can uniquely identify requests. It is also present in the response and error logs.
    Client ID - This identifies the application from which a request is sent.

*/
package com.mahfooz.kafka.request.header;

public class RequestHeader {

}
