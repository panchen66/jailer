package com.panchen.jailer.core.loadBalancer;

import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import com.panchen.jailer.core.common.DataTree.Node;

public class IntervalTimeWeightRandomLBRule implements LBRule {

    @Override
    public String choose(Set<Node> servers) {
        if (0 >= servers.size()) {
            return null;
        }
        long now = System.currentTimeMillis();
        ArrayBlockingQueue<Double> intervalTimeQ = new ArrayBlockingQueue<Double>(servers.size());
        ArrayBlockingQueue<Node> nodeQ = new ArrayBlockingQueue<Node>(servers.size());
        Double countIntervalTime = 0d;
        for (Node server : servers) {
            long intervalTime = now - server.lastCallTime;
            if (0 > intervalTime) {
                continue;
            }
            nodeQ.add(server);
            Double sqrtIntervalTime = Math.sqrt(intervalTime);
            intervalTimeQ.add(sqrtIntervalTime);
            countIntervalTime += sqrtIntervalTime;
        }
        double random = Math.random() * countIntervalTime;
        String preferredService = null;
        while (0 < random) {
            if (null == nodeQ.peek()) {
                return null;
            }
            random -= intervalTimeQ.poll();
            preferredService = nodeQ.poll().value;
        }
        return preferredService;
    }



}
