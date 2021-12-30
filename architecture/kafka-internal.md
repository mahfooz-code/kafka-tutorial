#   Cluster Membership

Kafka uses Apache ZooKeeper to maintain the list of brokers that are currently members of a cluster.

Every broker has a unique identifier that is either set in the broker configuration file or automatically generated.

Every time a broker process starts, it registers itself with its ID in ZooKeeper by creating an ephemeral node.
Kafka brokers, the controller, and some of the ecosystem tools subscribe to the /brokers/ids path in ZooKeeper where brokers are registered so that they get notified when brokers are added or removed.

If you try to start another broker with the same ID, you will get an error—the new broker will try to register but fail because we already have a ZooKeeper node for the same broker ID.

When a broker loses connectivity to ZooKeeper (usually as a result of the broker stopping, but this can also happen as a result of network partition or a long garbage-collection pause), the ephemeral node that the broker created when starting will be automatically removed from ZooKeeper. Kafka components that are watching the list of brokers will be notified that the broker is gone.

Even though the node representing the broker is gone when the broker is stopped, the broker ID still exists in other data structures. 

#   The Controller

1) The controller is one of the Kafka brokers that is responsible for electing partition leaders.
2) controller by creating an ephemeral node in ZooKeeper called /controller.
3) zookeeper.session.timeout.ms
4) Each time a controller is elected, it receives a new, higher controller epoch number.
5) Kafka uses ZooKeeper’s ephemeral node feature to elect a controller and to notify the controller when nodes join and leave the cluster.
6) The controller is responsible for electing leaders among the partitions and replicas whenever it notices nodes join and leave the cluster.

#   Zookeeper
ZooKeeper has two important functions: 
1) it is used to elect a controller 
2) to store the cluster metadata—registered brokers, configuration, topics, partitions and replicas.
3) 
#   KRaft: Kafka’s New Raft-Based Controller

1) 


#   Replicas

1) replica.lag.time.max.ms
2) ISR: Replicas that are consistently asking for the latest messages are called in-sync replicas.
3) preferred leader: each partition has a preferred leader—the replica that was the leader when the topic was originally created.
4) auto.leader.rebalance.enable=true
5) The first replica in the list is always the preferred leader.



#   Ports and Threads

1) acceptor thread
2) network thread/processor thread
3) I/O thread

#   Queues

1) request queue
2) response queue
3) 


#   Request

1) metadata request 
   1) metadata.max.age.ms
   2) 

2) produce request
   1) acks
   2) purgatory
   3) 
3) fetch request
   1) limit
   2) replica.lag.time.max.ms
   3) 
4) admin request



#Physical storage

1) log.dirs
2) Tiered Storage
3) Partition Allocation
4) File Management
   1) Segment: Active and Passive
   2) kafka-run-class kafka.tools.DumpLogSegments
   3) File Format
   4) 


#Indexes

#Compaction
1) clean
   1) log.cleaner.enabled
   2) min.compaction.lag.ms
   3) max.compaction.lag.ms
2) dirty
3) 



