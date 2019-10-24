package ru.sbt.mipt.oop.alarm;

public enum AlarmType {
    ACTIVATION {
        public State getState() {
            return new ActivationState();
        }
    }, DEACTIVATION {
        public State getState() {
            return new DeactivationState();
        }
    }, ALARM {
        public State getState() {
            return new AlarmState();
        }
    };

    public abstract State getState();
}
