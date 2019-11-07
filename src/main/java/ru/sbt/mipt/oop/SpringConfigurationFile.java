package ru.sbt.mipt.oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.adapter.AdapterStateHandler;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.readers.JsonReaderSmartHome;
import ru.sbt.mipt.oop.readers.Reader;

@Configuration
public class SpringConfigurationFile {
    @Bean
    Reader<SmartHome> reader() {
        return new JsonReaderSmartHome();
    }

    @Bean
    Alarm alarm() {
        return new Alarm("1234");
    }

    @Bean
    SmartHome smartHome() {
        return reader().read();
    }

    @Bean
    StateHandler stateHandler() {
        return new AdapterStateHandler(smartHome(), alarm());
    }
}
