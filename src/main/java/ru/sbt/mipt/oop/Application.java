package ru.sbt.mipt.oop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String... args) {
        // считываем состояние дома из файла
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfigurationFile.class);
        StateHandler stateHandler = context.getBean(StateHandler.class);
        stateHandler.stateHandle();
        // начинаем цикл обработки событий
    }
}
