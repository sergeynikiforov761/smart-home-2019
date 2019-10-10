package ru.sbt.mipt.oop;

public class ConsoleSimpleMessageSender<T> implements Sender {

    private String message;
    private T command;

    public ConsoleSimpleMessageSender(String message, T command){
        this.message = message;
        this.command = command;
    }

    @Override
    public void sendCommand() {
        System.out.println(this.message  + this.command);
    }
}
