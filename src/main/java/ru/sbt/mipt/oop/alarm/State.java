package ru.sbt.mipt.oop.alarm;

public interface State {
    State act(boolean status, AlarmType type);

    AlarmType getClassAvailability();
}
