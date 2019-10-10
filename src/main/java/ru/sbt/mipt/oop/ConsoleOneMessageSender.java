package ru.sbt.mipt.oop;

public class ConsoleOneMessageSender<T> implements Sender {

    private String message;
    private T command;

    public ConsoleOneMessageSender(String message, T command){
        this.message = message;
        this.command = command;
    }

    public void sendCommand() {
        System.out.println(this.message  + this.command);
    }



}
