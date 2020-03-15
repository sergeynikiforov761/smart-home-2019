package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.sensor.*;

public class NextSensorEventGetter implements NextEventGetter<SensorEvent> {

    @Override
    public SensorEvent getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        double random = Math.random();
        if (random < 0.32d) {
            String objectId = "" + ((int) (4 * Math.random()) + 1);
            return new LightSensorEvent(LightEventType.values()[(int) (2 * Math.random())], objectId);
        } else if (0.32d <= random && random < 0.64d) {
            String objectId = "" + ((int) (10 * Math.random()) + 1);
            return new DoorSensorEvent(DoorEventType.values()[(int) (2 * Math.random())], objectId);
        } else if (0.64d <= random && random < 0.96d) {
            String code = "" + ((int) (2 * Math.random()));
            return new AlarmSensorEvent(AlarmEventType.values()[(int) (2 * Math.random())], code);
        }
        return null;
    }
}