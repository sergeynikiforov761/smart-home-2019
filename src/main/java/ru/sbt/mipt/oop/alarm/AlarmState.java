package ru.sbt.mipt.oop.alarm;

public class AlarmState implements State {

    private Alarm alarm;

    public AlarmState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if(code.equals(alarm.getCode())){
            alarm.changeState(new ActivationState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {
        if(code.equals(alarm.getCode())){
            alarm.changeState(new DeactivationState(alarm));
        }
    }

    /*
    @Override
    public State act(boolean status, AlarmType type) {
        if (!status) {
            return type.getState();
        } else return this;
    }

    @Override
    public AlarmType getClassAvailability() {
        return AlarmType.ALARM;
    }
     */
}
