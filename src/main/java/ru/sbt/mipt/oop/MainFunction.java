package ru.sbt.mipt.oop;

public class MainFunction {
    public static SensorEvent processing(SensorEvent event, SmartHome smartHome) {
        System.out.println("Got event: " + event);
        new ProcessingLightEvent(event, smartHome).processEvent();
        new ProcessingDoorEvent(event, smartHome).processEvent();
        new ProcessingHallDoorEvent(event, smartHome).processEvent();
        return new NextSensorEvent().getNextEvent();
    }
}
