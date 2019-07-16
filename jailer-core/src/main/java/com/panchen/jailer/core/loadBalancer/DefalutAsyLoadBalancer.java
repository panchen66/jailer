package com.panchen.jailer.core.loadBalancer;

import java.util.Set;
import java.util.HashMap;
import org.springframework.stereotype.Component;

import com.panchen.jailer.core.common.DataTree;
import com.panchen.jailer.core.common.DataTree.Node;

@Component
public class DefalutAsyLoadBalancer implements LoadBalancer {

    // synchronization problem?
    private static HashMap<String, String> preferredServices = new HashMap<>();
    
    private DataTree dataTree;
    private LBRule lbRule;

    @Override
    public String chooseServer(Set<Node> servers) {
        //
        return lbRule.choose(servers);
    }

    @Override
    public Set<Node> getServers(String key) {
        return dataTree.get(key);
    }

    @Override
    public void setLBRule(LBRule lbRule) {
        this.lbRule = lbRule;
    }



}
