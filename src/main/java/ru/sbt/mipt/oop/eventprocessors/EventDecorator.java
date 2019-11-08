package ru.sbt.mipt.oop.eventprocessors;


import ru.sbt.mipt.oop.alarm.ActivationAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationAlarmState;

public class EventDecorator implements ProcessingEvent {

    private ProcessingEvent processingEvent;
    private Alarm alarm;

    public EventDecorator(ProcessingEvent processingEvent, Alarm alarm) {
        this.processingEvent = processingEvent;
        this.alarm = alarm;
    }

    @Override
    public void processEvent() {
        if (alarm.getState() instanceof DeactivationAlarmState) {
            processingEvent.processEvent();
        } else if (alarm.getState() instanceof ActivationAlarmState) {
            alarm.danger();
            System.out.println("Sending sms to phone: 8-800-55-35-35");
        }
    }
}
