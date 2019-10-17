package ru.sbt.mipt.oop;

import java.util.function.BiFunction;

public interface IteratorAction<T, E> {
    void actIterator(BiFunction<T, E, Void> func);
}
