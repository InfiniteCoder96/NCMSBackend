package com.spark.ncms.entity;

public enum SeverityLevel {
    RECOVERED,
    MINOR,
    MAJOR,
    CRITICAL;

    public String getName() {
        return this.name();
    }
}
