package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.*;
import ru.sbt.mipt.oop.eventprocessors.adapter.AdapterEventHandler;
import ru.sbt.mipt.oop.eventprocessors.adapter.converters.*;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.readers.JsonReaderSmartHome;

import java.util.Collection;

@Configuration
@Import(RemoteControlConfiguration.class)
public class SpringConfigurationFile {

    @Bean
    SensorEventsManager sensorEventsManager(ProcessingEvent eventProcessor, CCSensorEventConverter ccSensorEventConverter){
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(new AdapterEventHandler(eventProcessor, ccSensorEventConverter));
        return sensorEventsManager;
    }

    @Bean
    public ProcessingEvent eventProcessor(Collection<ProcessingEvent> collectionEventProcess) {
        return new EventDecorator(new EventProcessor(collectionEventProcess), alarm());
    }

    @Bean
    ProcessingEvent processingLightEvent(){
        return new ProcessingLightEvent(smartHome());
    }

    @Bean
    ProcessingEvent processingHallDoorEvent(){
        return new ProcessingHallDoorEvent(smartHome());
    }

    @Bean
    ProcessingEvent processingDoorEvent(){
        return new ProcessingDoorEvent(smartHome());
    }

    @Bean
    ProcessingEvent processingAlarmEvent(){
        return new ProcessingAlarmEvent(alarm());
    }

    @Bean
    SmartHome smartHome() {
        return new JsonReaderSmartHome().read();
    }

    @Bean
    Alarm alarm() {
        return new Alarm("1234");
    }


    @Bean
    public CCSensorEventConverter ccSensorEventConverter(LightsIsOffConverter lightsIsOffConverter) {
        return new LightsIsOnConverter(lightsIsOffConverter);
    }

    @Bean
    public LightsIsOffConverter lightsIsOffConverter(DoorIsOpenedConverter doorIsOpenedConverter) {
        return new LightsIsOffConverter(doorIsOpenedConverter);
    }

    @Bean
    public DoorIsOpenedConverter doorIsOpenedConverter(DoorIsClosedConverter doorIsClosedConverter) {
        return new DoorIsOpenedConverter(doorIsClosedConverter);
    }

    @Bean
    DoorIsClosedConverter doorIsClosedConverter() {
        return new DoorIsClosedConverter(null);
    }
}
