package ru.sbt.mipt.oop.alarm;

public class ActivationState implements State {

    private Alarm alarm;

    public ActivationState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (code.equals(alarm.getCode())) {
            alarm.changeState(new DeactivationState(alarm));
        } else {
            alarm.changeState(new AlarmState(alarm));
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
        return AlarmType.ACTIVATION;
    }
     */
}
