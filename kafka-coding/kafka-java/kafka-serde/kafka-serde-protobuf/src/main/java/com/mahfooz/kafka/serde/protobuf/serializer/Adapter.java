package com.mahfooz.kafka.serde.protobuf.serializer;

import java.util.Map;

public abstract class Adapter {
    public void close() {}
    public void configure(Map<String,?> configs, boolean isKey) {}
}