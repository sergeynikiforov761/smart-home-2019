package ru.sbt.mipt.oop.alarm;

public interface AlarmState {

    void activate(String code);

    void deactivate(String code);
}
