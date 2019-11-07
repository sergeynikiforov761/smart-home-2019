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
        if (event.getEventType().equals("LightIsOn")) {
            return new SensorEvent(LIGHT_ON, event.getObjectId());
        } else if (event.getEventType().equals("LightIsOff")) {
            return new SensorEvent(LIGHT_OFF, event.getObjectId());
        } else if (event.getEventType().equals("DoorIsOpen") || event.getEventType().equals("DoorIsUnlocked")) {
            return new SensorEvent(DOOR_OPEN, event.getObjectId());
        } else {
            return new SensorEvent(DOOR_CLOSED, event.getObjectId());
        }
    }
}
