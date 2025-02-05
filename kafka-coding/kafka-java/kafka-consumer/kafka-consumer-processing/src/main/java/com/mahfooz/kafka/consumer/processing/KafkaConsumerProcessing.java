/*

Startup Protocol

    API_VERSION
        At startup, the first step is to match protocol versions.
        It is possible that the broker and the consumer are of different versions (the broker is older and the consumer is newer, or
        vice versa).
        This matching is done by the API_VERSIONS request.

    METADATA
        The next step is to collect cluster information, such as the addresses of all the brokers (prior to this point we used the
        bootstrap server as a reference), partition counts, and partition leaders.
        This is done in the METADATA request.

    FIND_COORDINATOR
        After acquiring the metadata, the consumer has the information needed to join the group.
        By this time on the broker side, a coordinator has been selected per consumer group.
        The consumers must find their coordinator with the FIND_COORDINATOR request.

    JOIN_GROUP
        After finding the coordinator, the consumer(s) are ready to join the group.
        Every consumer in the group sends their own member-specific metadata to the coordinator in the JOIN_GROUP request.
        The coordinator waits until all the consumers have sent their request, then assigns a leader for the group.
        At the response plus the collected metadata are sent to the leader, so it knows about its group.

    SYNC_GROUP
        The remaining step is to assign partitions to consumers and propagate this state.
        Similar to the previous request, all consumers send a SYNC_GROUP request to the coordinator; the leader provides the assignments
        in this request.
        After it receives the sync request from each group member, the coordinator propagates this member state in the response.
        By the end of this step, the consumers are ready and can start.

Consumption Protocol

    When consuming, the first step is to query where should the consumer start.
    This is done in the OFFSET_FETCH request.
    This is not mandatory: the consumer can also provide the offset manually.
    After this, the consumer is free to pull data from the broker.
    Data consumption happens in the FETCH requests.
    These are the long-pull requests.
    They are answered only when the broker has enough data; the request can be outstanding for a longer period of time.

    OFFSET_FETCH
    FETCH
    OFFSET_COMMIT
    FETCH
    HEARTBEAT

Shutdown Protocol
    The last step when the consumption is done is to shut down the consumer gracefully.
    This is done in one single step, called the LEAVE_GROUP protocol.

 */
package com.mahfooz.kafka.consumer.processing;

public class KafkaConsumerProcessing {
}
