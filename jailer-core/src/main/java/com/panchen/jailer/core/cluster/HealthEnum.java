package com.panchen.jailer.core.cluster;

/**
 * 
 * no start - white
 * Including communication recovery before recovery - yellow 
 * After communicating with the cluster and join the cluster - green 
 * Current instance closure - red
 * 
 * @author pc
 *
 */
public enum HealthEnum {

    GREEN("green", 0), YELLO("yellow", 1), RED("red", 2), White("white", 3);

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
