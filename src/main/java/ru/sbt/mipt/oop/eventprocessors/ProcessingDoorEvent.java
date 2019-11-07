package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;

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
        smartHome.execute(object -> {
            if (event.getType() == DOOR_CLOSED || event.getType() == DOOR_OPEN) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(object_new -> {
                        if (object_new instanceof Door) {
                            Door door = (Door) object_new;
                            updateDoorState(event, door, room);
                        }
                    });
                }
            }
        });
    }


    private void updateDoorState(SensorEvent event, Door door, Room room) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setStatus(true);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
            } else {
                door.setStatus(false);
                System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
            }
        }
    }

}
