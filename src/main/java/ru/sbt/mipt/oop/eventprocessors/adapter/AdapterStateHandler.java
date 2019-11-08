package ru.sbt.mipt.oop.eventprocessors.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.StateHandler;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class AdapterStateHandler extends StateHandler {

    private SensorEventsManager sensorEventsManager = new SensorEventsManager();
    private SmartHome smartHome;
    private Alarm alarm;

    public AdapterStateHandler(SmartHome smartHome, Alarm alarm) {
        super(smartHome, alarm);
        this.alarm = alarm;
        this.smartHome = smartHome;
    }

    public void stateHandle() {
        sensorEventsManager.registerEventHandler(
                event -> new AdapterEventHandler(this, smartHome, alarm).handleEvent(event));
        sensorEventsManager.start();
    }

    public SensorEvent toSensorEvent(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                return new SensorEvent(LIGHT_ON, event.getObjectId());
            case "LightIsOff":
                return new SensorEvent(LIGHT_OFF, event.getObjectId());
            case "DoorIsOpen":
            case "DoorIsUnlocked":
                return new SensorEvent(DOOR_OPEN, event.getObjectId());
            case "DoorIsClosed":
            case "DoorIsLocked":
                return new SensorEvent(DOOR_CLOSED, event.getObjectId());
            default:
                return new SensorEvent(null, event.getObjectId());
        }
    }
}
