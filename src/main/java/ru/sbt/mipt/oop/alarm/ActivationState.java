package ru.sbt.mipt.oop.alarm;

public class ActivationState implements State {

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
}
