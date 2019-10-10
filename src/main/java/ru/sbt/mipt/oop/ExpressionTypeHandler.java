package ru.sbt.mipt.oop;

public class ExpressionTypeHandler<T> implements ExpressionHandler{

    private SensorEvent event;
    private T type;

    public ExpressionTypeHandler(SensorEvent event, T type){
        this.event = event;
        this.type = type;
    }

    @Override
    public boolean expressionHandle(){
        return event.getType() == type;
    }
}
