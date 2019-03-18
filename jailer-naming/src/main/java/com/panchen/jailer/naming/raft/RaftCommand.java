package com.panchen.jailer.naming.raft;

import com.panchen.jailer.core.command.Command;
import com.panchen.jailer.core.command.Receiver;

public class RaftCommand extends Command {
    
    public RaftCommand(Receiver receiver) {
        super(receiver);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void beforeExecute() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void afterExecute() {
        // TODO Auto-generated method stub
        
    }

}
