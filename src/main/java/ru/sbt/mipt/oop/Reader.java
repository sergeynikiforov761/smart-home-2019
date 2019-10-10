package ru.sbt.mipt.oop;

import java.io.IOException;

public interface Reader<T> {
    public T read() throws IOException;
}
