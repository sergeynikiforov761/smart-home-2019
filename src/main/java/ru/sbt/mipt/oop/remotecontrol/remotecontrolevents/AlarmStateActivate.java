package ru.sbt.mipt.oop.remotecontrol.remotecontrolevents;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmStateActivate implements EventAction{

    private Alarm alarm;

    public AlarmStateActivate(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void execute() {
        alarm.danger();
    }
}
