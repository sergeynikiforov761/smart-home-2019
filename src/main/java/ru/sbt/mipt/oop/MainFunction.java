package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.alarm.DeactivationAlarmState;
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
        if (alarm.getState() instanceof DangerAlarmState) {
            System.out.println("Sending sms to phone: 8-800-55-35-35");
        } else {
            for (ProcessingEvent processingEvent : processingEvents) {
                new EventDecorator(processingEvent, alarm).processEvent();
                // здесь можно прописать логику смены статуса/пароля сигнализации
                if (alarm.getState() instanceof DeactivationAlarmState) {
                    alarm.activate("1234");
                }
            }
        }
    }
}