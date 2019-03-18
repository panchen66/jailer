package com.panchen.jailer.core.loadBalancer;

import java.util.Set;

import com.panchen.jailer.core.common.DataTree.Node;

public interface LoadBalancer {

    public String chooseServer(Set<Node> servers);


    public void markServer(Node node);


    public Set<Node> getAllServers(Object key);

}
