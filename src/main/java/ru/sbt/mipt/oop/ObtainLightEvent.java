package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class ObtainLightEvent implements ObtainEvent{

    private SensorEvent event;
    private SmartHome smartHome;

    public ObtainLightEvent(SensorEvent event, SmartHome smartHome){
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void obtainEvent() {
        if (new ExpressionComplexTypeHandler<>(event, "||", LIGHT_ON, LIGHT_OFF).expressionHandle()){
            // событие от источника света
            for (Room room : smartHome.getRooms()) {
                for (Light light : room.getLights()) {
                    if (new ExpressionIdHandler(light, event).expressionHandle()){
                        if (new ExpressionTypeHandler<>(event, LIGHT_ON).expressionHandle()){
                            new EventComplexHandler(light, room,true, "Light ", " in room ", " was turned on.").eventHandle();
                        } else {
                            new EventComplexHandler(light, room,false, "Light ", " in room ", " was turned off.").eventHandle();
                        }
                    }
                }
            }
        }
    }
}
