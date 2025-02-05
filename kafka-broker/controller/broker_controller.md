# Broker controller

The controller broker is responsible for setting up leader/follower relationships for all partitions of a topic.

Kafka uses ZooKeeper to elect the controller broker.

If the controlling broker fails or becomes unavailable for any reason, ZooKeeper elects a new controller from a set of brokers
that are considered to be caught up with the leader (an in-sync replica [ISR]).

The brokers that make up this set are dynamic, and ZooKeeper recognizes only brokers in this set for election as leader

The controller broker is responsible for setting up leader/follower relationships for all partitions of a topic.

If a Kafka node dies or is unresponsive (to ZooKeeper heartbeats), all of its assigned partitions (both leader and follower) are
reassigned by the controller broker.
