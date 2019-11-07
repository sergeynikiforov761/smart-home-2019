package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.ProcessingEvent;

import java.util.Collection;

public class MainFunction {
    private SensorEvent event;
    private Collection<ProcessingEvent> processingEvents;

    public MainFunction(SensorEvent event, Collection<ProcessingEvent> processingEvents) {
        this.event = event;
        this.processingEvents = processingEvents;
    }

    public void processing() {
        System.out.println("Got event: " + event);
        for (ProcessingEvent processingEvent : processingEvents) {
            processingEvent.processEvent();
        }
    }
}
