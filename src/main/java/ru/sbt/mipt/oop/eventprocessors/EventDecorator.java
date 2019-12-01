package ru.sbt.mipt.oop.eventprocessors;


import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarm.ActivationAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DangerAlarmState;
import ru.sbt.mipt.oop.sensor.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensor.AlarmEventType.ALARM_DEACTIVATE;

public class EventDecorator implements ProcessingEvent {

    private EventProcessor eventProcessor;
    private Alarm alarm;

    public EventDecorator(EventProcessor eventProcessor, Alarm alarm) {
        this.eventProcessor = eventProcessor;
        this.alarm = alarm;
    }

    @Override
    public void processEvent(SensorEvent event) {
        System.out.println("Got event: " + event);
        if (alarm.getState() instanceof ActivationAlarmState) {
            alarm.danger();
        } else if (alarm.getState() instanceof DangerAlarmState) {
            processAlarmState(event);
        } else eventProcessor.processEvent(event);
    }

    private void processAlarmState(SensorEvent event) {
        if (event instanceof AlarmSensorEvent && ((AlarmSensorEvent) event).getType() == ALARM_DEACTIVATE) {
            eventProcessor.processEvent(event);
        } else {
            System.out.println("Sending sms to phone: 8-800-55-35-35");
        }
    }
}
