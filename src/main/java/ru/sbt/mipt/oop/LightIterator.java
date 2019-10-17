package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class LightIterator implements Iterator<Light>, IteratorAction<Light, Room> {

    private List<Room> rooms;
    private int roomLocation = 0;
    private int lightLocation = 0;

    public LightIterator(SmartHome smartHome) {
        rooms = new ArrayList<>(smartHome.getRooms());
    }

    @Override
    public Light next() {
        if (hasMore()) {
            List<Light> lights = new ArrayList<>(rooms.get(roomLocation).getLights());
            Light light = lights.get(lightLocation);
            lightLocation++;
            return light;
        } else {
            return null;
        }
    }

    @Override
    public boolean hasMore() {
        if (roomLocation < rooms.size()) {
            if (lightLocation < rooms.get(roomLocation).getLights().size()) {
                return true;
            } else {
                roomLocation++;
                lightLocation = 0;
                return hasMore();
            }
        }
        return false;
    }

    public Room getCurrentRoom() {
        return rooms.get(roomLocation);
    }

    @Override
    public void actIterator(BiFunction<Light, Room, Void> func) {
        while (hasMore()) {
            Light light = next();
            func.apply(light, getCurrentRoom());
        }
    }
}
