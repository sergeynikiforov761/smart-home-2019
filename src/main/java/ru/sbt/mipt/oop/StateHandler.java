package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.EventDecorator;

public class StateHandler {

    private EventDecorator decorator;

    public StateHandler(EventDecorator decorator) {
        this.decorator = decorator;
    }

    public void stateHandle() {
        SensorEvent event = new NextSensorEventGetter().getNextEvent();
        while (event != null) {
            decorator.processEvent(event);
            event = new NextSensorEventGetter().getNextEvent();
        }
    }
}