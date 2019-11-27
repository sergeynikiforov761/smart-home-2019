package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.readers.*;

import java.util.ArrayList;
import java.util.Collection;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла

//        Reader<SmartHome> jsonReader = new JsonReaderSmartHome();
//        SmartHome smartHome = jsonReader.read();
//        // начинаем цикл обработки событий
//        Alarm alarm = new Alarm("1");
//        Collection<ProcessingEvent> processingEvents = new ArrayList<>();
//        processingEvents.add(new ProcessingLightEvent(smartHome));
//        processingEvents.add(new ProcessingDoorEvent(smartHome));
//        processingEvents.add(new ProcessingHallDoorEvent(smartHome));
//        processingEvents.add(new ProcessingAlarmEvent(alarm));
//        EventDecorator decorator = new EventDecorator(new EventProcessor(processingEvents), alarm);
//        new StateHandler(decorator).stateHandle();
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        SensorEventsManager sensorEventsManager = context.getBean(SensorEventsManager.class);
        sensorEventsManager.start();

    }
}
