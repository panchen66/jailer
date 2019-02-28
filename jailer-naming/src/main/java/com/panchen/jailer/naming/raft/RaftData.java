package com.panchen.jailer.naming.raft;

import java.util.concurrent.atomic.AtomicLong;
import com.panchen.jailer.core.command.Command;

public class RaftData {

    public AtomicLong terms;

    public AtomicLong seq;

    public Command cmd;
    

}
