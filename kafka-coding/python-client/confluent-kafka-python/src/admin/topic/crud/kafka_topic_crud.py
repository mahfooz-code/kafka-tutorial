from confluent_kafka import (
    KafkaException,
    ConsumerGroupTopicPartitions,
    TopicPartition,
    ConsumerGroupState,
    TopicCollection,
    IsolationLevel,
    ConsumerGroupType,
    ElectionType,
)
from confluent_kafka.admin import (
    AdminClient,
    NewTopic,
    NewPartitions,
    ConfigResource,
    ConfigEntry,
    ConfigSource,
    AclBinding,
    AclBindingFilter,
    ResourceType,
    ResourcePatternType,
    AclOperation,
    AclPermissionType,
    AlterConfigOpType,
    ScramMechanism,
    ScramCredentialInfo,
    UserScramCredentialUpsertion,
    UserScramCredentialDeletion,
    OffsetSpec,
)


def create_topics(admin: AdminClient, topics: list[str]):
    """Create topics"""

    new_topics = [
        NewTopic(topic, num_partitions=3, replication_factor=1) for topic in topics
    ]
    # Call create_topics to asynchronously create topics, a dict
    # of <topic,future> is returned.
    fs = admin.create_topics(new_topics)

    # Wait for operation to finish.
    # Timeouts are preferably controlled by passing request_timeout=15.0
    # to the create_topics() call.
    # All futures will finish at the same time.
    for topic, f in fs.items():
        try:
            f.result()  # The result itself is None
            print(f"Topic {topic} created")
        except Exception as e:  # noqa
            print(f"Failed to create topic {topic}: {e}")


def delete_topics(admin: AdminClient, topics: list[str]):
    """delete topics"""

    # Call delete_topics to asynchronously delete topics, a future is returned.
    # By default this operation on the broker returns immediately while
    # topics are deleted in the background. But here we give it some time (30s)
    # to propagate in the cluster before returning.
    #
    # Returns a dict of <topic,future>.
    fs = admin.delete_topics(topics, operation_timeout=30)

    # Wait for operation to finish.
    for topic, f in fs.items():
        try:
            f.result()  # The result itself is None
            print(f"Topic {topic} deleted")
        except Exception as e:
            print(f"Failed to delete topic {topic}: {e}")


def create_partitions(admin: AdminClient, topics: list[str]):
    """create partitions"""

    new_parts = [
        NewPartitions(topic, int(new_total_count))
        for topic, new_total_count in zip(topics[0::2], topics[1::2])
    ]

    # Try switching validate_only to True to only validate the operation
    # on the broker but not actually perform it.
    fs = admin.create_partitions(new_parts, validate_only=False)

    # Wait for operation to finish.
    for topic, f in fs.items():
        try:
            f.result()  # The result itself is None
            print("Additional partitions created for topic {}".format(topic))
        except Exception as e:
            print("Failed to add partitions to topic {}: {}".format(topic, e))


def print_config(config, depth):
    print(
        "%40s = %-50s  [%s,is:read-only=%r,default=%r,sensitive=%r,synonym=%r,synonyms=%s]"
        % (
            (" " * depth) + config.name,
            config.value,
            ConfigSource(config.source),
            config.is_read_only,
            config.is_default,
            config.is_sensitive,
            config.is_synonym,
            [
                "%s:%s" % (x.name, ConfigSource(x.source))
                for x in iter(config.synonyms.values())
            ],
        )
    )


def example_describe_configs(a, args):
    """describe configs"""

    resources = [
        ConfigResource(restype, resname)
        for restype, resname in zip(args[0::2], args[1::2])
    ]

    fs = a.describe_configs(resources)

    # Wait for operation to finish.
    for res, f in fs.items():
        try:
            configs = f.result()
            for config in iter(configs.values()):
                print_config(config, 1)

        except KafkaException as e:
            print("Failed to describe {}: {}".format(res, e))
        except Exception:
            raise
