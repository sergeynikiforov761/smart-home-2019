package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.sensor.LightEventType;
import ru.sbt.mipt.oop.sensor.LightSensorEvent;

public class ProcessingLightEvent implements ProcessingEvent {

    private SmartHome smartHome;

    public ProcessingLightEvent(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof LightSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute((objectNew) -> {
                        if (objectNew instanceof Light) {
                            Light light = (Light) objectNew;
                            updateLightState((LightSensorEvent) event, light, room);
                        }
                    });
                }
            }
        });
    }

    private void updateLightState(LightSensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LightEventType.LIGHT_OFF) {
                light.setStatus(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setStatus(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
