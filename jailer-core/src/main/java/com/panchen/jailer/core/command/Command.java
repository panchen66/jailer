package com.panchen.jailer.core.command;

public abstract class Command {

    private Receiver receiver;

    public Command(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }

    public abstract void beforeExecute();

    public abstract void afterExecute();

}
