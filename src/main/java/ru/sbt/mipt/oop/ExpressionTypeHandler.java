package ru.sbt.mipt.oop;

public class ExpressionTypeHandler implements ExpressionHandler{

    private SensorEvent event;
    private SensorEventType type;

    public ExpressionTypeHandler(SensorEvent event, SensorEventType type){
        this.event = event;
        this.type = type;
    }

    @Override
    public boolean expressionHandle(){
        return event.getType() == type;
    }
}
