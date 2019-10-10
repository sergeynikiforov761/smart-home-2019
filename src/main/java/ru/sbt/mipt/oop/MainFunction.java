package ru.sbt.mipt.oop;

public class MainFunction {
    public static void processing(SensorEvent event, SmartHome smartHome){
        while (event != null) {
            Sender eventMessage = new ConsoleSimpleMessageSender<>("Got event: ", event);
            eventMessage.sendCommand();
            new ObtainLightEvent(event, smartHome).obtainEvent();
            new ObtainDoorEvent(event, smartHome).obtainEvent();
            event = NextSensorEvent.getNextSensorEvent();
        }

    }
}
