package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;
import ru.sbt.mipt.oop.sensor.DoorEventType;
import ru.sbt.mipt.oop.sensor.DoorSensorEvent;

public class ProcessingDoorEvent implements ProcessingEvent {

    private SmartHome smartHome;

    public ProcessingDoorEvent(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent(SensorEvent event) {
        smartHome.execute(object -> {
            if (event instanceof DoorSensorEvent) {
                if (object instanceof Room) {
                    Room room = (Room) object;
                    room.execute(object_new -> {
                        if (object_new instanceof Door) {
                            Door door = (Door) object_new;
                            updateDoorState((DoorSensorEvent) event, door, room);
                        }
                    });
                }
            }
        });
    }


    private void updateDoorState(DoorSensorEvent event, Door door, Room room) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DoorEventType.DOOR_OPEN) {
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
