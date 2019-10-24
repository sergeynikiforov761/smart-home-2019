package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.ActivationState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmProcessor;
import ru.sbt.mipt.oop.alarm.AlarmType;

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
            new AlarmProcessor(processingEvent, alarm).act();
            // здесь можно прописать логику смены статуса/пароля сигнализации
            if (alarm.getClassAvailability() == AlarmType.DEACTIVATION) {
                alarm.changeState(new ActivationState(), "1234");
            }
        }
        return new NextSensorEvent().getNextEvent();
    }
}
