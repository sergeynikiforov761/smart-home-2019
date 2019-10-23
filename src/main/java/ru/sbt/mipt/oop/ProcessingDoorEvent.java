package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class ProcessingDoorEvent implements ProcessingEvent {

    private SensorEvent event;
    private SmartHome smartHome;

    public ProcessingDoorEvent(SensorEvent event, SmartHome smartHome){
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void processEvent() {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED){
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (door.getId().equals(event.getObjectId())){
                        if (event.getType() == DOOR_OPEN) {
                            new EventComplexHandler(door, room,true, "Door "," in room "," was opened.").eventHandle();
                        } else {
                            new EventComplexHandler(door, room, false, "Door ", " in room ", " was closed.").eventHandle();
                            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        }
                    }
                }
            }
        }
    }
}
