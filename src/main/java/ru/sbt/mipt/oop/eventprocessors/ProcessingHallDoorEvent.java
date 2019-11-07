package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

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
        smartHome.execute((object) -> {
            if (event.getType() == DOOR_CLOSED) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    if (room.getName().equals("hall")) {
                        room.execute((object_new) -> {
                            if (object_new instanceof Door) {
                                Door door = (Door) object_new;
                                if (door.getId().equals(event.getObjectId())) {
                                    room.execute(object_new_light -> {
                                        if (object_new_light instanceof Light) {
                                            Light light = (Light) object_new_light;
                                            light.setStatus(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            System.out.println("Pretent we're sending command " + command);
                                        }
                                    });
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
