package com.panchen.jailer.core.command;

public abstract class Command {
    
    private Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public abstract void Execute();

}
