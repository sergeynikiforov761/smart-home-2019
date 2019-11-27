package ru.sbt.mipt.oop.eventprocessors.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.ProcessingEvent;
import ru.sbt.mipt.oop.eventprocessors.adapter.converters.CCSensorEventConverter;

public class AdapterEventHandler implements EventHandler {

    private ProcessingEvent processingEvent;
    private CCSensorEventConverter ccSensorEventConverter;

    public AdapterEventHandler(ProcessingEvent processingEvent, CCSensorEventConverter ccSensorEventConverter) {
        this.processingEvent = processingEvent;
        this.ccSensorEventConverter = ccSensorEventConverter;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        processingEvent.processEvent(ccSensorEventConverter.convert(event));
    }
}
