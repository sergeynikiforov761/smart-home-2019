package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.sensor.AlarmSensorEvent;

import static ru.sbt.mipt.oop.sensor.AlarmEventType.ALARM_ACTIVATE;

public class ProcessingAlarmEvent implements ProcessingEvent {
    private Alarm alarm;

    public ProcessingAlarmEvent(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void processEvent(SensorEvent event) {
        if (event instanceof AlarmSensorEvent) {
            if (((AlarmSensorEvent) event).getType() == ALARM_ACTIVATE) {
                alarm.activate(((AlarmSensorEvent) event).getCode());
            }
            else {
                alarm.deactivate(((AlarmSensorEvent) event).getCode());
            }
        }
    }
}
