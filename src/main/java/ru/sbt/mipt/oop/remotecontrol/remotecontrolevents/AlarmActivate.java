package ru.sbt.mipt.oop.remotecontrol.remotecontrolevents;

import ru.sbt.mipt.oop.alarm.Alarm;

public class AlarmActivate implements EventAction{

    private Alarm alarm;
    private String code;

    public AlarmActivate(Alarm alarm, String code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void execute() {
        alarm.activate(code);
    }
}
