package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.CommandType;
import ru.sbt.mipt.oop.SensorCommand;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.DeviceAction;
import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;

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
        Action action = new DeviceAction((objectFirst, objectSecond) -> {
            if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
                if (objectFirst instanceof Door) {
                    Door door = (Door) objectFirst;
                    if (door.getId().equals(event.getObjectId())) {
                        if (event.getType() == DOOR_CLOSED) {
                            if (objectSecond instanceof Room) {
                                Room room = (Room) objectSecond;
                                if (room.getName().equals("hall")) {
                                    Action newAction = new DeviceAction((objectFirst_, objectSecond_) -> {
                                        if (objectFirst_ instanceof Light) {
                                            Light light = (Light) objectFirst_;
                                            light.setStatus(false);
                                            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                                            System.out.println("Pretent we're sending command " + command);
                                        }
                                        return null;
                                    });
                                    room.execute(newAction);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        });
        smartHome.execute(action);
    }
}
