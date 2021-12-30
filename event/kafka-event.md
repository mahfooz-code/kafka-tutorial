#   Header

    Application-level headers contain optional metadata about an event.


#   Key
    Keys are also optional, but play an important role in how data is distributed across partitions.
    We will see this over the next few chapters, but generally speaking, they are used to identify related records.


#   Value

#   Timestamp
    
    Each event is associated with a timestamp.

#   Value
    The value contains the actual message contents, encoded as a byte array. It’s up to clients to deserialize the raw bytes into a more meaningful structure (e.g., a JSON object or Avro record). 
    
