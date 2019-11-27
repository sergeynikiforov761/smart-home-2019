package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;

import java.util.Collection;

public class EventProcessor implements ProcessingEvent {
    private Collection<ProcessingEvent> processingEventCollection;

    public EventProcessor(Collection<ProcessingEvent> processingEventCollection) {
        this.processingEventCollection = processingEventCollection;
    }

    @Override
    public void processEvent(SensorEvent event) {
        for(ProcessingEvent eventProcessor: processingEventCollection){
            eventProcessor.processEvent(event);
        }
    }
}

