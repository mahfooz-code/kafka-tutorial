/*
These instructions assume you are running ZooKeeper in replicated mode. A minimum of three servers are required for replicated mode, and you must have an odd number of servers for failover. For more information, see the ZooKeeper documentation.

Navigate to the ZooKeeper properties file (/etc/kafka/zookeeper.properties) file and modify as shown.

tickTime=2000
dataDir=/var/lib/zookeeper/
clientPort=2181
initLimit=5
syncLimit=2
server.1=zoo1:2888:3888
server.2=zoo2:2888:3888
server.3=zoo3:2888:3888
autopurge.snapRetainCount=3
autopurge.purgeInterval=24
This configuration is for a three node ensemble. This configuration file should be identical across all nodes in the ensemble. tickTime, dataDir, and clientPort are all set to typical single server values. The initLimit and syncLimit govern how long following ZooKeeper servers can take to initialize with the current leader and how long they can be out of sync with the leader. In this configuration, a follower can take 10000 ms to initialize and can be out of sync for up to 4000 ms based on the tickTime being set to 2000ms.

The server.* properties set the ensemble membership. The format is

server.<myid>=<hostname>:<leaderport>:<electionport>
myid is the server identification number. There are three servers that each have a different myid with values 1, 2, and 3 respectively. The myid is set by creating a file named myid in the dataDir that contains a single integer in human readable ASCII text. This value must match one of the myid values from the configuration file. You will see an error if another ensemble member is already started with a conflicting myid value.
leaderport is used by followers to connect to the active leader. This port should be open between all ZooKeeper ensemble members.
electionport is used to perform leader elections between ensemble members. This port should be open between all ZooKeeper ensemble members.
The autopurge.snapRetainCount and autopurge.purgeInterval have been set to purge all but three snapshots every 24 hours.

Navigate to the ZooKeeper log directory (e.g., /var/lib/zookeeper/) and create a file named myid. The myid file consists of a single line that contains the machine ID in the format <machine-id>. When the ZooKeeper server starts up, it knows which server it is by referencing the myid file. For example, server 1 will have a myid value of 1.

 */
package com.mahfooz.kafka.config.zookeeper;

public class Zookeeper {
}
