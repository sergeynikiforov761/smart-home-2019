package ru.sbt.mipt.oop.alarm;

public class DeactivationState implements State {

    @Override
    public State act(boolean status, AlarmType type) {
        if (type.equals(AlarmType.ACTIVATION) || type.equals(AlarmType.ALARM)) {
            if (!status) {
                return new AlarmState();
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    @Override
    public AlarmType getClassAvailability() {
        return AlarmType.DEACTIVATION;
    }
}
