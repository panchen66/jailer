package com.panchen.jailer.core.loadBalancer;

import java.util.Set;

import com.panchen.jailer.core.common.DataTree.Node;

public interface LBRule {

    public String choose(Set<Node> servers);

}
