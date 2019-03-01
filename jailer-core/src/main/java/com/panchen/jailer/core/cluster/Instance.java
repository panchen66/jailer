package com.panchen.jailer.core.cluster;

import java.io.File;

public class Instance {

    public String ip;

    public int port;

    public boolean isLeader;

    public long snapShotCount;

    public File snapShotFolder;
}
