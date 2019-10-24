package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.EventDecorator;
import ru.sbt.mipt.oop.ProcessingEvent;

public class AlarmProcessor {

    private Alarm alarm;
    private ProcessingEvent event;

    public AlarmProcessor(ProcessingEvent event, Alarm alarm) {
        this.alarm = alarm;
        this.event = event;
    }

    public void act() {
        if (alarm.getClassAvailability() == AlarmType.DEACTIVATION) {
            new EventDecorator(event).processEvent();
        } else if (alarm.getClassAvailability() == AlarmType.ALARM) {
            System.out.println("Alarm");
        } else {
            alarm.changeState(new AlarmState(), alarm.getCode());
        }
    }
}
