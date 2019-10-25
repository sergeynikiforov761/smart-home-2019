package ru.sbt.mipt.oop;


import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.AlarmState;
import ru.sbt.mipt.oop.alarm.AlarmType;

public class EventDecorator {

    private ProcessingEvent processingEvent;
    private Alarm alarm;

    public EventDecorator(ProcessingEvent processingEvent, Alarm alarm) {
        this.processingEvent = processingEvent;
        this.alarm = alarm;
    }

    public void processEvent() {
        if (alarm.getClassAvailability() == AlarmType.DEACTIVATION) {
            processingEvent.processEvent();
        } else if (alarm.getClassAvailability() == AlarmType.ALARM) {
            System.out.println("Alarm");
        } else {
            alarm.changeState(new AlarmState(), alarm.getCode());
        }
    }
}
