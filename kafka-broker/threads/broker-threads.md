# Broker thread

## Acceptor thread

The broker runs an acceptor thread on each port it is listening to.

The acceptor thread creates a client connection, and hands over the connection to the processor thread, also called network thread.

## processor / network thread

The processor thread takes requests from client connections and places them in a Request Queue.

From the Response Queue, the process thread picks up the response and sends back to clients.

## IO thread

From the Request Queue, the I/O thread picks up the request, processes them and places them in a Response Queue.
