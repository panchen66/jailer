package com.panchen.jailer.core.cluster;

public enum HealthEnum {

    GREEN("green", 0), YELLO("yello", 1), RED("red", 2), White("white", 3);

    private String key;
    private int value;

    private HealthEnum(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public int value() {
        return value;
    }

    public String key() {
        return key;
    }
}
