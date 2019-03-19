package com.panchen.jailer.core.loadBalancer;

import java.util.Set;

import com.panchen.jailer.core.common.DataTree.Node;

public interface LoadBalancer {

    public String chooseServer(Set<Node> servers);

    public Set<Node> getServers(String key);
    
    public void setLBRule(LBRule lbRule);
}
