package ru.sbt.mipt.oop.alarm;

public class AlarmState implements State {

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
}
