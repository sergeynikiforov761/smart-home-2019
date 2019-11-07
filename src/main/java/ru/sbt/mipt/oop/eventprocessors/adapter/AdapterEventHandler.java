package ru.sbt.mipt.oop.eventprocessors.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.MainFunction;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEvent;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEventCreator;
import ru.sbt.mipt.oop.homeelements.SmartHome;

import java.util.Collection;

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
        Collection<ProcessingEvent> processingEvents = new ProcessingEventCreator(adapterStateHandler.toSensorEvent(event), smartHome).processingEventCreate();
        new MainFunction(adapterStateHandler.toSensorEvent(event), processingEvents, alarm).processing();
    }
}
