package ru.sbt.mipt.oop.eventprocessors;


import ru.sbt.mipt.oop.SensorEvent;
import ru.sbt.mipt.oop.alarm.ActivationAlarmState;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.alarm.DeactivationAlarmState;

public class EventDecorator implements ProcessingEvent {

    private ProcessingEvent processingEvent;
    private Alarm alarm;
    private SensorEvent event;
    private boolean flag;

    public EventDecorator(ProcessingEvent processingEvent, Alarm alarm) {
        this.processingEvent = processingEvent;
        this.alarm = alarm;
    }

    public EventDecorator() {}

    public void changeEventAndAlarm(ProcessingEvent processingEvent, Alarm alarm){
        this.processingEvent = processingEvent;
        this.alarm = alarm;
    }

    public void eventChange(SensorEvent event){
        this.event = event;
        this.flag = true;
    }

    @Override
    public void processEvent() {
        if (processingEvent instanceof ProcessingAlarmEvent) {
            processingEvent.processEvent();
        }
        else if (alarm.getState() instanceof DeactivationAlarmState) {
            processingEvent.processEvent();
        } else if (alarm.getState() instanceof ActivationAlarmState) {
            alarm.danger();
            if(flag){
                System.out.println("Sending sms to phone: 8-800-55-35-35");
                flag = false;
            }
        } else {
            if(flag){
                System.out.println("Sending sms to phone: 8-800-55-35-35");
                flag = false;
            }
        }
    }
}
