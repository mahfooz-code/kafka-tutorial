package com.mahfooz.kafka.rest;

import io.confluent.rest.RestConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;

import java.util.Map;

/**
 * Configuration classes are a convenient place to pass global configuration settings for your
 * application around. rest-utils requires a few methods to be implemented (e.g. getPort() to
 * indicate what port the server should listen on), but all of which have reasonable default
 * implementations.
 */
public class HelloWorldRestConfig extends RestConfig {
    private static ConfigDef config;

    public static final String GREETING_CONFIG = "greeting";
    private static final String GREETING_CONFIG_DOC = "Greeting template for responses.";
    private static final String GREETING_CONFIG_DEFAULT = "Hello, %s!";

    static {
        config = baseConfigDef()
                .define(GREETING_CONFIG,
                        Type.STRING,
                        GREETING_CONFIG_DEFAULT,
                        Importance.HIGH,
                        GREETING_CONFIG_DOC);
    }

    public HelloWorldRestConfig() {
        super(config);
    }

    public HelloWorldRestConfig(Map<?, ?> props) {
        super(config, props);
    }
}