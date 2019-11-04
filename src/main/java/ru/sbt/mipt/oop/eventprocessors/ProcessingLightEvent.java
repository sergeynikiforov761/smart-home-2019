package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.DeviceAction;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.SensorEventType.LIGHT_ON;

public class ProcessingLightEvent implements ProcessingEvent {

    private SensorEvent event;
    private SmartHome smartHome;

    public ProcessingLightEvent(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent() {
        Action action = new DeviceAction((objectFirst, objectSecond) -> {
            if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                if (objectSecond instanceof Room) {
                    Room room = (Room) objectSecond;
                    if (objectFirst instanceof Light) {
                        Light light = (Light) objectFirst;
                        updateLightState(event, light, room);
                    }
                }

            }
            return null;
        });
        smartHome.execute(action);
    }

    private void updateLightState(SensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setStatus(true);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
            } else {
                light.setStatus(false);
                System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
            }
        }
    }
}
