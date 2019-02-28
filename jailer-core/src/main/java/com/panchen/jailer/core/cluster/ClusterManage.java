package com.panchen.jailer.core.cluster;

import java.util.concurrent.ConcurrentLinkedDeque;

import org.springframework.stereotype.Component;

@Component
public class ClusterManage {

    // if null , means client
    public Instance self;
    public ConcurrentLinkedDeque<Instance> instances = new ConcurrentLinkedDeque<>();
    public transient int health;

    public Instance getLeader() throws Exception {
        if (self.isLeader) {
            return self;
        }
        if (null != instances) {
            instances.peekFirst();
        }
        throw new Exception("now no leader");
    }

    public int health() {
        return health;
    }
}
