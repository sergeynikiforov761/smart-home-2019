package ru.sbt.mipt.oop.remotecontrol.remotecontrolevents;

import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Room;
import ru.sbt.mipt.oop.homeelements.SmartHome;

public class HallDoorClosed implements EventAction{
    private SmartHome smartHome;

    public HallDoorClosed(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Room) {
                Room room = (Room) object;
                if (room.getName().equals("Hall")) {
                    room.execute(object_new -> {
                                Door door = (Door) object_new;
                                door.setStatus(false);
                            }
                    );
                }
            }
        });
    }
}