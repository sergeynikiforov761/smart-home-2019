package ru.sbt.mipt.oop;

import java.io.IOException;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonReaderSmartHome("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();
        // начинаем цикл обработки событий
        SensorEvent event = NextSensorEvent.getNextSensorEvent();
        MainFunction.processing(event, smartHome);
    }
}
