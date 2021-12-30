#   request.timeout.ms
    This configuration limits the time that your application can spend waiting for AdminClient to respond.
    This includes the time spent on retrying if the client receives a retriable error.
    The default value is 120 seconds, which is quite long, but some AdminClient operations, especially consumer group management commands, can take a while to respond.

#   Essential Topic Management
    