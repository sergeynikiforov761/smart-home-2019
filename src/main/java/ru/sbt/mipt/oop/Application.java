package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.readers.JsonReaderSmartHome;
import ru.sbt.mipt.oop.readers.Reader;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        Reader<SmartHome> jsonReader = new JsonReaderSmartHome("smart-home-1.js");
        SmartHome smartHome = jsonReader.read();
        // начинаем цикл обработки событий
        NextEventGetter<SensorEvent> sensorEvent = new NextSensorEventGetter();
        SensorEvent event = sensorEvent.getNextEvent();
        new StateHandler(event, smartHome).stateHandle();
    }
}
