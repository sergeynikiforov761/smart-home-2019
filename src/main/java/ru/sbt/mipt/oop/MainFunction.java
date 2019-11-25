package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.EventDecorator;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEvent;

import java.util.Collection;

public class MainFunction {
    private SensorEvent event;
    private Collection<ProcessingEvent> processingEvents;
    private Alarm alarm;

    public MainFunction(SensorEvent event, Collection<ProcessingEvent> processingEvents, Alarm alarm) {
        this.event = event;
        this.processingEvents = processingEvents;
        this.alarm = alarm;
    }

    public void processing() {
        System.out.println("Got event: " + event);
        EventDecorator eventDecorator = new EventDecorator();
        eventDecorator.eventChange(event);
        for (ProcessingEvent processingEvent : processingEvents) {
            eventDecorator.changeEventAndAlarm(processingEvent, alarm);
            eventDecorator.processEvent();
        }
    }
}