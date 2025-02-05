# Co-ordinator

The special broker which manages the group on the broker side is called the `coordinator`.

The coordinator handles `heartbeats` and assigns the `leader`.

Every group has a `coordinator` that organizes the `startup` of a `consumer group` and assist whenever a `consumer` leaves the group.
