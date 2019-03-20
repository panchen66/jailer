package com.panchen.jailer.core.cluster;

import java.io.File;

import com.panchen.jailer.core.common.DataTree;

public class Instance {

    public String ip;

    public int port;

    public boolean isLeader;

    public long snapShotCount;

    public File snapShotFolder;
    
    public DataTree dataTree;
    
    public long snapShotInterval;
}
