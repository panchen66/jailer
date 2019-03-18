package com.panchen.jailer.naming.raft;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.panchen.jailer.core.cluster.ClusterManage;
import com.panchen.jailer.core.cluster.HealthEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RaftManage {

    @Autowired
    ClusterManage clusterManage;

    private Logger logger = LoggerFactory.getLogger(RaftManage.class);

    private ConcurrentLinkedDeque<RaftData> pendingDq = new ConcurrentLinkedDeque<>();
    private ConcurrentLinkedQueue<RaftData> ackQ = new ConcurrentLinkedQueue<>();

    private ExecutorService cachedThreadPool = Executors.newFixedThreadPool(
            1 > Runtime.getRuntime().availableProcessors() ? 1 : Runtime.getRuntime().availableProcessors() / 2 + 1);

    private void init() {
        cachedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                while (HealthEnum.White.value() != clusterManage.health()) {
                    try {
                        if (null != clusterManage.getLeader()) {
                            RaftData ackData = ackQ.poll();
                            if (null != ackData) {
                                // analysis cmd to match pendingData
                            }
                        }
                    } catch (Exception e) {
                        logger.error("RaftManage : {}", e);
                    }
                }
            }
        });
    }
}
