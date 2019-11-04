package ru.sbt.mipt.oop.alarm;

public class AlarmStateDecorator {
    private State state;

    public AlarmStateDecorator(State state) {
        this.state = state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }
}