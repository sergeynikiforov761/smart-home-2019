package ru.sbt.mipt.oop;

public class StateHandler {

    private SensorEvent event;
    private SmartHome smartHome;

    public StateHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public void stateHandle() {
        while (event != null) {
            event = MainFunction.processing(event, smartHome);
        }
    }
}
