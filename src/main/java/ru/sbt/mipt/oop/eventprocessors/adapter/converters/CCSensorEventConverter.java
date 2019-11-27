package ru.sbt.mipt.oop.eventprocessors.adapter.converters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.SensorEvent;

public interface CCSensorEventConverter {
    SensorEvent convert(CCSensorEvent ccSensorEvent);
}
