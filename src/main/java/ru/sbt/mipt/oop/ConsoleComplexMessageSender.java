package ru.sbt.mipt.oop;

public class ConsoleComplexMessageSender<T, K> implements Sender {

    private String messageFirst;
    private String messageSecond;
    private String messageThird;
    private T commandFirst;
    private K commandSecond;

    public ConsoleComplexMessageSender(String messageFirst, String messageSecond, String messageThird, T commandFirst, K commandSecond){
        this.commandFirst = commandFirst;
        this.commandSecond = commandSecond;
        this.messageFirst = messageFirst;
        this.messageSecond = messageSecond;
        this.messageThird = messageThird;
    }

    @Override
    public void sendCommand() {
        System.out.println(this.messageFirst + this.commandFirst + this.messageSecond + this.commandSecond + this.messageThird);
    }
}
