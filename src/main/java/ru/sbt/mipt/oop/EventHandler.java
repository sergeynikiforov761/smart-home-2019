package ru.sbt.mipt.oop;

public class EventHandler {

    public static void eventHandle(HomeComponents homeComponent, Room room, boolean status, String firstMessage, String secondMessage, String thirdMessage){
        StatusChanger.setStatus(homeComponent, status);
        Sender consoleMessage = new ConsoleThreeMessageSender<>(firstMessage, secondMessage, thirdMessage, homeComponent.getId(), room.getName());
        consoleMessage.sendCommand();
    }

    public static<T> void eventHandle(HomeComponents homeComponent, boolean status, String message, T command){
        StatusChanger.setStatus(homeComponent, status);
        Sender consoleMessage = new ConsoleOneMessageSender<>(message, command);
        consoleMessage.sendCommand();
    }
}
