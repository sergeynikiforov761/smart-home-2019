package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class ObtainDoorEvent implements ObtainEvent{

    private SensorEvent event;
    private SmartHome smartHome;

    public ObtainDoorEvent(SensorEvent event, SmartHome smartHome){
        this.event = event;
        this.smartHome = smartHome;
    }

    @Override
    public void obtainEvent() {
        if (new ExpressionComplexTypeHandler<>(event, "||", DOOR_OPEN, DOOR_CLOSED).expressionHandle()){
            // событие от двери
            for (Room room : smartHome.getRooms()) {
                for (Door door : room.getDoors()) {
                    if (new ExpressionIdHandler(door, event).expressionHandle()){
                        if (new ExpressionTypeHandler<>(event, DOOR_OPEN).expressionHandle()) {
                            new EventComplexHandler(door, room,true, "Door "," in room "," was opened.").eventHandle();
                        } else {
                            new EventComplexHandler(door, room, false, "Door ", " in room ", " was closed.").eventHandle();
                            // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                            // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                            if (new ExpressionStringHandler(room, "hall").expressionHandle()){
                                for (Room homeRoom : smartHome.getRooms()) {
                                    for (Light light : homeRoom.getLights()) {
                                        new EventSimpleHandler<>(light, false, "Pretent we're sending command ", new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*

     */
}
