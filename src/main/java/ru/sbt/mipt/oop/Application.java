package ru.sbt.mipt.oop;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonReaderSmartHome("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();
        // начинаем цикл обработки событий
        NextEventGetter<SensorEvent> sensorEvent = new NextSensorEvent();
        SensorEvent event = sensorEvent.getNextEvent();
        new StateHandler(event, smartHome).stateHandle();
    }
}