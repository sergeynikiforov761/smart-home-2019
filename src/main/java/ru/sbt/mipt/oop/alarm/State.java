package ru.sbt.mipt.oop.alarm;

public interface State {
    // State act(boolean status, AlarmType type);
    void activate(String code);

    void deactivate(String code);
    // AlarmType getClassAvailability();
}
