package ru.sbt.mipt.oop;

public class EventSimpleHandler<T> implements EventHandler{

    private HomeComponent homeComponent;
    private boolean status;
    private String message;
    private T command;

    public EventSimpleHandler(HomeComponent homeComponent, boolean status, String message, T command){
        this.homeComponent = homeComponent;
        this.message = message;
        this.command = command;
        this.status = status;
    }

    public void eventHandle(){
        StatusChanger.setStatus(homeComponent, status);
        Sender consoleMessage = new ConsoleSimpleMessageSender<>(message, command);
        consoleMessage.sendCommand();
    }
}
