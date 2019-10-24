package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class ProcessingDoorEvent implements ProcessingEvent {

    private SensorEvent event;
    private SmartHome smartHome;

    public ProcessingDoorEvent(SensorEvent event, SmartHome smartHome) {
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent() {
        smartHome.execute((object, room) -> {
                    if (event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN) {
                        if (object instanceof Door) {
                            Door door = (Door) object;
                            updateDoorState(event, door, room);
                        }
                    }
                    return null;
                }
        );
    }


    private void updateDoorState(SensorEvent event, Door door, Room room) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                new EventComplexHandler(door, room, true, "Door ", " in room ", " was opened.").eventHandle();
            } else {
                new EventComplexHandler(door, room, false, "Door ", " in room ", " was closed.").eventHandle();
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            }
        }
    }

}
