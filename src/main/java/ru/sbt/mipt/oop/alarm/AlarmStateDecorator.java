package ru.sbt.mipt.oop.alarm;

public class AlarmStateDecorator {
    private State state;

    public AlarmStateDecorator(State state) {
        this.state = state;
    }

    public AlarmType getClassAvailability() {
        return state.getClassAvailability();
    }
}