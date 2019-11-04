package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmType;
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

    public SensorEvent processing() {
        System.out.println("Got event: " + event);
        for (ProcessingEvent processingEvent : processingEvents) {
            new EventDecorator(processingEvent, alarm).processEvent();
            // здесь можно прописать логику смены статуса/пароля сигнализации
            if (alarm.getType() == AlarmType.DEACTIVATION) {
                alarm.activate("1234");
            }
        }
        return new NextSensorEventGetter().getNextEvent();
    }
}
