package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

public class SmartHome {
    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
