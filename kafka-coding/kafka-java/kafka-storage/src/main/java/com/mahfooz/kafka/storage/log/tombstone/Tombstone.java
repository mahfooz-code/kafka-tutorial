/*

Deletion of Messages from Segments
To delete the message of a key completely from the system, the application needs to produce a message with that key with a NULL value.

When the compaction thread finds the key with the NULL value, regular compaction is done replacing the segment with the key with the NULL value.

This message with the key with the NULL value is also called a tombstone message and is retained for a configured time.

When a consumer sees a tombstone message, it knows that the value is deleted.

After the configured time, the compaction thread will delete the tombstone message, and the key will be completely removed from the partition log.

With a cleanup.policy of compact, you might wonder how you can
remove a record from the log. With a compacted topic, deletion provides a
null value for the given key, setting a tombstone marker. Any key with a null
value ensures that any prior record with the same key is removed, and the
tombstone marker itself is removed after a period of time.

 */
package com.mahfooz.kafka.storage.log.tombstone;

public class Tombstone {
}
