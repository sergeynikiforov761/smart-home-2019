package ru.sbt.mipt.oop.alarm;

public enum AlarmType {
    ACTIVATION {
        public State getState(Alarm alarm) {
            return new ActivationState(alarm);
        }
    }, DEACTIVATION {
        public State getState(Alarm alarm) {
            return new DeactivationState(alarm);
        }
    }, ALARM {
        public State getState(Alarm alarm) {
            return new AlarmState(alarm);
        }
    };

    public abstract State getState(Alarm alarm);
}
