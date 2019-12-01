package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;

public interface ProcessingEvent {
    void processEvent(SensorEvent event);
}
