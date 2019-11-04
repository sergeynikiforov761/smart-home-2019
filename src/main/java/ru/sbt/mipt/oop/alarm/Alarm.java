package ru.sbt.mipt.oop.alarm;

public class Alarm implements State {

    private State state;
    private String code;

    public Alarm(String code) {
        this.state = new DeactivationState(this);
        this.code = code;
    }


    public void changeState(State state) {
        this.state = state;
    }

    public AlarmType getType() {
        if (this.state instanceof ActivationState) {
            return AlarmType.ACTIVATION;
        } else if (this.state instanceof DeactivationState) {
            return AlarmType.DEACTIVATION;
        } else {
            return AlarmType.ALARM;
        }
    }

    public String getCode() {
        return code;
    }

    @Override
    public void activate(String code) {
        new AlarmStateDecorator(state).activate(code);
    }

    @Override
    public void deactivate(String code) {
        new AlarmStateDecorator(state).deactivate(code);
    }
}