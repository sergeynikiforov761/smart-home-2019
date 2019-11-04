package ru.sbt.mipt.oop;

class NextSensorEventGetter implements NextEventGetter<SensorEvent> {

    @Override
    public SensorEvent getNextEvent() {
        // pretend like we're getting the eventprocessors from physical world, but here we're going to just generate some random eventprocessors
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }
}