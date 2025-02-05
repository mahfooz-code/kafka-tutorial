/*


1)  The broker runs an acceptor thread on each port it is listening to.

2)  The acceptor thread creates a client connection, and hands over the connection to the processor thread, also 
    called network thread.

3)  The processor thread takes requests from client connections and places them in a Request Queue.

4)  From the Request Queue, the I/O thread picks up the request, processes them and places them in a Response Queue.

5)  From the Response Queue, the process thread picks up the response and sends back to clients.

*/
package com.mahfooz.kafka.request.working;

public class Working {

}
