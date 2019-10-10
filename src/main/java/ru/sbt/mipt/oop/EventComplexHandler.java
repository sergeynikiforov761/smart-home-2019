package ru.sbt.mipt.oop;

public class EventComplexHandler implements EventHandler {

    private HomeComponent homeComponent;
    private Room room;
    private boolean status;
    private String firstMessage;
    private String secondMessage;
    private String thirdMessage;

    public EventComplexHandler(HomeComponent homeComponent, Room room, boolean status, String firstMessage, String secondMessage, String thirdMessage){
        this.firstMessage = firstMessage;
        this.homeComponent = homeComponent;
        this.room = room;
        this.secondMessage = secondMessage;
        this.thirdMessage = thirdMessage;
        this.status = status;
    }

    @Override
    public void eventHandle() {
        StatusChanger.setStatus(homeComponent, status);
        Sender consoleMessage = new ConsoleComplexMessageSender<>(firstMessage, secondMessage, thirdMessage, homeComponent.getId(), room.getName());
        consoleMessage.sendCommand();
    }
}
