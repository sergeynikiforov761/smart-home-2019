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
        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
        smartHome.execute((object, room) -> {
                    if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                        if (object instanceof Door) {
                            Door door = (Door) object;
                            if (door.getId().equals(event.getObjectId())) {
                                if (event.getType() == DOOR_CLOSED) {
                                    if (room.getName().equals("hall")) {
                                        room.execute((object_, room_) -> {
                                                    if (object_ instanceof Light) {
                                                        Light light = (Light) object_;
                                                        new EventSimpleHandler<>(light, false, "Pretent we're sending command ", new SensorCommand(CommandType.LIGHT_OFF, light.getId())).eventHandle();
                                                    }
                                                    return null;
                                                }
                                        );

                                    }
                                }
                            }
                        }
                    }
                    return null;
                }
        );
    }
}
