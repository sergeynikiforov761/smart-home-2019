package ru.sbt.mipt.oop.eventprocessors.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.StateHandler;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.homeelements.SmartHome;

public class AdapterEventHandler implements EventHandler {

    private SmartHome smartHome;
    private Alarm alarm;
    private AdapterStateHandler adapterStateHandler;

    public AdapterEventHandler(AdapterStateHandler adapterStateHandler, SmartHome smartHome, Alarm alarm) {
        this.smartHome = smartHome;
        this.alarm = alarm;
        this.adapterStateHandler = adapterStateHandler;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        new StateHandler(adapterStateHandler.toSensorEvent(event), smartHome, alarm).stateHandle();
    }
}
