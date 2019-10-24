package ru.sbt.mipt.oop.alarm;

public class Alarm implements State {

    private State state;
    private String code;

    public Alarm(State state, String code) {
        this.state = state;
        this.code = code;
    }

    @Override
    public State act(boolean status, AlarmType type) {
        return state.act(status, type);
    }

    @Override
    public AlarmType getClassAvailability() {
        return new AlarmStateDecorator(state).getClassAvailability();
    }

    public void changeState(State state, String code) {
        AlarmType currentType = getType();
        this.state = state;
        this.state = act(this.code.equals(code), currentType);
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

    public String getCode(){
        return code;
    }
}