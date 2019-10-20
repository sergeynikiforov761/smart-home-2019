package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.BiFunction;

public class SmartHome implements Actionable {
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

    @Override
    public void execute(BiFunction<Object, Room, Void> function) {
        for (Room room : rooms) {
            room.execute(function);
        }
    }
}
