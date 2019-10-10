package ru.sbt.mipt.oop;

public class ExpressionIdHandler implements ExpressionHandler {

    private HomeComponent homeComponent;
    private SensorEvent event;

    public ExpressionIdHandler(HomeComponent homeComponent, SensorEvent event){
        this.event = event;
        this.homeComponent = homeComponent;
    }

    @Override
    public boolean expressionHandle() {
        return homeComponent.getId().equals(event.getObjectId());
    }
}
