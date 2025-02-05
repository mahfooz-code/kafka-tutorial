#Get the Debezium connector
confluent-hub install --component-dir confluent-hub-components --no-prompt debezium/debezium-connector-mysql:1.1.0

docker-compose up

#Configure MySQL for Debezium
docker exec -it mysql /bin/bash
mysql -u root -p

GRANT ALL PRIVILEGES ON *.* TO 'mysql' WITH GRANT OPTION;
ALTER USER 'mysql'@'%' IDENTIFIED WITH mysql_native_password BY 'mysql';
FLUSH PRIVILEGES;

#Create the calls table in MySQL
USE call-center;

CREATE TABLE calls (name TEXT, reason TEXT, duration_seconds INT);

#
INSERT INTO calls (name, reason, duration_seconds) VALUES ("michael", "purchase", 540);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("michael", "help", 224);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("colin", "help", 802);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("derek", "purchase", 10204);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("derek", "help", 600);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("colin", "refund", 105);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("michael", "help", 2030);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("colin", "purchase", 800);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("derek", "help", 2514);
INSERT INTO calls (name, reason, duration_seconds) VALUES ("derek", "refund", 325);


#Start the Debezium connector
docker exec -it ksqldb-cli ksql http://ksqldb-server:18088

SET 'auto.offset.reset' = 'earliest';

CREATE SOURCE CONNECTOR calls_reader WITH (
    'connector.class' = 'io.debezium.connector.mysql.MySqlConnector',
    'database.hostname' = 'mysql',
    'database.port' = '3306',
    'database.user' = 'mysql',
    'database.password' = 'mysql',
    'database.allowPublicKeyRetrieval' = 'true',
    'database.server.id' = '184054',
    'database.server.name' = 'ksqldb-db',
    'database.whitelist' = 'ksqldb',
    'database.history.kafka.bootstrap.servers' = 'broker:9092',
    'database.history.kafka.topic' = 'call-center',
    'table.whitelist' = 'ksqldb.calls',
    'include.schema.changes' = 'false'
);

SHOW TOPICS;

PRINT 'ksqldb-db.ksqldb.calls' FROM BEGINNING;


DESCRIBE CONNECTOR calls_reader;

CREATE STREAM calls WITH (
    kafka_topic = 'call-center-db.call-center.calls',
    value_format = 'avro'
);

CREATE TABLE support_view AS
    SELECT after->name AS name,
           count_distinct(after->reason) AS distinct_reasons,
           latest_by_offset(after->reason) AS last_reason
    FROM calls
    GROUP BY after->name
    EMIT CHANGES;


CREATE TABLE lifetime_view AS
    SELECT after->name AS name,
           count(after->reason) AS total_calls,
           (sum(after->duration_seconds) / 60) as minutes_engaged
    FROM calls
    GROUP BY after->name
    EMIT CHANGES;


    SELECT name, distinct_reasons, last_reason
FROM support_view
WHERE name = 'derek';

SELECT name, total_calls, minutes_engaged
FROM lifetime_view
WHERE name = 'michael';

docker-compose down
