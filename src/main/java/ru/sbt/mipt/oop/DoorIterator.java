package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class DoorIterator implements IteratorAction<Door, Room>, Iterator<Door> {

    private List<Room> rooms;
    private int roomLocation = 0;
    private int doorLocation = 0;

    public DoorIterator(SmartHome smartHome) {
        rooms = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public Door next() {
        if (hasMore()) {
            List<Door> doors = new ArrayList<>(rooms.get(roomLocation).getDoors());
            Door door = doors.get(doorLocation);
            doorLocation++;
            return door;
        }
        return null;
    }

    @Override
    public boolean hasMore() {
        if (roomLocation < rooms.size()) {
            if (doorLocation < rooms.get(roomLocation).getDoors().size()) {
                return true;
            } else {
                roomLocation++;
                doorLocation = 0;
                return hasMore();
            }
        }
        return false;
    }

    public Room getCurrentRoom() {
        return this.rooms.get(roomLocation);
    }

    @Override
    public void actIterator(BiFunction<Door, Room, Void> func) {
        while (hasMore()) {
            Door door = next();
            Room room = getCurrentRoom();
            func.apply(door, room);
        }
    }
}
