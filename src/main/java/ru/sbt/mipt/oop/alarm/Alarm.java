package ru.sbt.mipt.oop.alarm;

public class Alarm implements AlarmState {

    private AlarmState alarmState;
    private String code;

    public Alarm(String code) {
        this.alarmState = new DeactivationAlarmState(this);
        this.code = code;
    }


    public void changeState(AlarmState state) {
        this.alarmState = state;
    }

    public String getCode() {
        return code;
    }

    public AlarmState getState(){
        return alarmState;
    }

    public void danger() {
        this.alarmState = new DangerAlarmState(this);
    }

    @Override
    public void activate(String code) {
        new AlarmStateDecorator(alarmState).activate(code);
    }

    @Override
    public void deactivate(String code) {
        new AlarmStateDecorator(alarmState).deactivate(code);
    }
}