package ru.sbt.mipt.oop.alarm;

public class DeactivationState implements State {

    private Alarm alarm;

    public DeactivationState(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activate(String code) {
        if(alarm.getCode().equals(code)){
            alarm.changeState(new ActivationState(alarm));
        }
    }

    @Override
    public void deactivate(String code) {
        if(!alarm.getCode().equals(code)){
            alarm.changeState(new AlarmState(alarm));
        }
    }
    /*
    @Override
    public State act(boolean status, AlarmType type) {
        if (type.equals(AlarmType.ACTIVATION) || type.equals(AlarmType.ALARM)) {
            if (!status) {
                return new AlarmState();
            } else {
                return this;
            }
        } else {
            return this;
        }
    }

    @Override
    public AlarmType getClassAvailability() {
        return AlarmType.DEACTIVATION;
    }
     */
}
