#   Source processors
    Sources are where information flows into the Kafka Streams application.
    Data is read from a Kafka topic and sent to one or more stream processors.

#   Stream processors
    These processors are responsible for applying data processing/transformation logic on 
    the input stream. 
    In the high-level DSL, these processors are defined using a set of built-in operators that are exposed by the Kafka Streams library, which we will be going over in detail in the following chapters.
    Some example operators are filter, map, flatMap, and join.

#   Sink processors
    Sinks are where enriched, transformed, filtered, or otherwise processed records are written back to Kafka, 
    either to be handled by another stream processing application or to be sent to a downstream data store via something like Kafka Connect.
    Like source processors, sink processors are connected to a Kafka topic.

#   processor topology
    A collection of processors forms a processor topology, which is often referred to as simply the topology in both this book and the wider Kafka Streams community.

#   Dataflow Programming
    

