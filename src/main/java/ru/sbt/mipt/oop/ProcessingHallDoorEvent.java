package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class ProcessingHallDoorEvent implements ProcessingEvent {
    private SensorEvent event;
    private SmartHome smartHome;

    public ProcessingHallDoorEvent(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent() {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_CLOSED) {
                            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                            if (room.getName().equals("hall")) {
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        new EventSimpleHandler<>(light, false, "Pretent we're sending command ", new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
