package com.mahfooz.kafka.schema.model;

import java.util.Date;

public final class HealthCheck {
    private String event;
    private String factory;
    private String serialNumber;
    private String type;
    private String status;
    private Date lastStartedAt;
    private float temperature;
    private String ipAddress;
}