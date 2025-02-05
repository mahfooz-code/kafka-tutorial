package com.mahfooz.kafka.subject.prop;

import okhttp3.MediaType;

public interface KafkaSchemaProp {

    String SCHEMA_BASE_URL="http://mtmdevhdoped01:8082";
    MediaType SCHEMA_CONTENT = MediaType.parse("application/vnd.schemaregistry.v1+json");
}
