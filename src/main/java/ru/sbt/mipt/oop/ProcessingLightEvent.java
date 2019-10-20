package ru.sbt.mipt.oop;

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

        smartHome.execute((object, room) -> {
                    if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
                        if (object instanceof Light) {
                            Light light = (Light) object;
                            updateLightState(event, light, room);
                        }
                    }
                    return null;
                }
        );
    }

    private void updateLightState(SensorEvent event, Light light, Room room) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                new EventComplexHandler(light, room, true, "Light ", " in room ", " was turned on.").eventHandle();
            } else {
                new EventComplexHandler(light, room, false, "Light ", " in room ", " was turned off.").eventHandle();
            }
        }
    }
}
