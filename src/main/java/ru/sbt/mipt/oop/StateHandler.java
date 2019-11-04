package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEvent;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEventCreator;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import java.util.Collection;

public class StateHandler {

    private SensorEvent event;
    private SmartHome smartHome;
    private Alarm alarm;

    public StateHandler(SensorEvent event, SmartHome smartHome, Alarm alarm) {
        this.event = event;
        this.smartHome = smartHome;
        this.alarm = alarm;
    }

    public StateHandler(SmartHome smartHome, Alarm alarm) {
        this.smartHome = smartHome;
        this.alarm = alarm;
    }

    public void stateHandle() {
        while (event != null) {
            Collection<ProcessingEvent> processingEvents = new ProcessingEventCreator(event, smartHome).processingEventCreate();
            event = new MainFunction(event, processingEvents, alarm).processing();
        }
    }
}

