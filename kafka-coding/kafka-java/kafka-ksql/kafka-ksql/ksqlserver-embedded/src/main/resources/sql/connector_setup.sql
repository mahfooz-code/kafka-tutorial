CREATE SOURCE CONNECTOR debezium_jdbc_source WITH (
       "connector.class" = 'io.debezium.connector.sqlserver.SqlServerConnector',
        "tasks.max" = 1,
        "key.converter"= 'org.apache.kafka.connect.json.JsonConverter',
        "value.converter"= 'org.apache.kafka.connect.json.JsonConverter',
        "value.converter.schemas.enable"= false,
        "key.converter.schemas.enable"= false,
        "database.server.name" = 'kafkaconnect',
        "database.hostname" = 'mtmdevhdoped01',
        "database.port" = '1433',
        "database.user" = 'sa',
        "database.password" = 'Move@123',
        "tombstones.on.delete"= false,
        "database.dbname" = 'move',
        "table.whitelist"= 'move.TB_MOVE_TRAFFIC_CDR_DETAILS',
        "database.history.kafka.bootstrap.servers" = 'broker:9092',
        "database.history.kafka.topic" = 'kafkaconnect.json.sqlserver');



CREATE SINK CONNECTOR hdfs_sink WITH (
    "connector.class" = 'io.confluent.connect.hdfs.HdfsSinkConnector',
    "tasks.max"= 2,
    "topics"= 'TRAFFIC_STREAMS',
    "hdfs.url"= 'hdfs://mtmdevhdopms01.intl.vsnl.co.in:8020/',
    "logs.dir"= '/user/hdfs/logs',
    "topics.dir"= '/user/hdfs/topics',
    "flush.size"= 3,
    "hive.integration"= true,
    "schema.compatibility" = 'BACKWARD',
    "value.converter"= 'org.apache.kafka.connect.json.JsonConverter',
    "schemas.enable" = false,
    "file.delim"= ';');