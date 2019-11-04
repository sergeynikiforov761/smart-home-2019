package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String... args) {
        // считываем состояние дома из файла
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        StateHandler stateHandler = context.getBean(StateHandler.class);
        stateHandler.stateHandle();
        // начинаем цикл обработки событий
    }
}
