package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.sensor.AlarmSensorEvent;

import java.util.ArrayList;
import java.util.Collection;

public class ProcessingEventCreator {

    private SensorEvent event;
    private SmartHome smartHome;
    private Alarm alarm;

    public ProcessingEventCreator(SensorEvent event, SmartHome smartHome, Alarm alarm) {
        this.event = event;
        this.smartHome = smartHome;
        this.alarm = alarm;
    }

    public Collection<ProcessingEvent> processingEventCreate() {
        Collection<ProcessingEvent> processingEvents = new ArrayList<>();
        processingEvents.add(new ProcessingLightEvent(event, smartHome));
        processingEvents.add(new ProcessingDoorEvent(event, smartHome));
        processingEvents.add(new ProcessingHallDoorEvent(event, smartHome));
        processingEvents.add(new ProcessingAlarmEvent(event, alarm));
        return processingEvents;
    }

}