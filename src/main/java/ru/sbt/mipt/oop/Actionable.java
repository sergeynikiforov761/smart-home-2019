package ru.sbt.mipt.oop;


import java.util.function.BiFunction;


public interface Actionable {
    void execute(BiFunction<Object, Room, Void> function);
}