package ru.sbt.mipt.oop;


public class EventDecorator {

    private ProcessingEvent processingEvent;

    public EventDecorator(ProcessingEvent processingEvent) {
        this.processingEvent = processingEvent;
    }

    public void processEvent() {
        processingEvent.processEvent();
    }
}
