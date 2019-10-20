package ru.sbt.mipt.oop;

import java.util.Collection;

public class StateHandler {

    private SensorEvent event;
    private SmartHome smartHome;

    public StateHandler(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public void stateHandle() {
        while (event != null) {
            Collection<ProcessingEvent> processingEvents = new ProcessingEventCreator(event, smartHome).processingEventCreate();
            event = new MainFunction(event, processingEvents).processing();
        }
    }
}
