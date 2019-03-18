package com.panchen.jailer.core.loadBalancer;

public interface LBRule {

    public String choose(Object key);

}
