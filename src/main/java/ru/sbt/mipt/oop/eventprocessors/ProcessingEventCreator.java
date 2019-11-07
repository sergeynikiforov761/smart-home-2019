package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

public class ProcessingEventCreator {

    private SensorEvent event;
    private SmartHome smartHome;

    public ProcessingEventCreator(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    public Collection<ProcessingEvent> processingEventCreate() {
        Collection<ProcessingEvent> processingEvents = new ArrayList<>();
        processingEvents.add(new ProcessingLightEvent(event, smartHome));
        processingEvents.add(new ProcessingDoorEvent(event, smartHome));
        processingEvents.add(new ProcessingHallDoorEvent(event, smartHome));
        return processingEvents;
    }

}