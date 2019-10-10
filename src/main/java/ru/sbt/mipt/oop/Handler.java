package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Handler {
    public static void processing(SensorEvent event, SmartHome smartHome){
        while (event != null) {
            Sender eventMessage = new ConsoleOneMessageSender<>("Got event: ", event);
            eventMessage.sendCommand();
            if (ExpressionHandler.expressionHandle(event, "||", LIGHT_ON, LIGHT_OFF)) {
                // событие от источника света
                for (Room room : smartHome.getRooms()) {
                    for (Light light : room.getLights()) {
                        if (light.getId().equals(event.getObjectId())) {
                            if (ExpressionHandler.expressionHandle(event, LIGHT_ON)) {
                                EventHandler.eventHandle(light, room,true, "Light ", " in room ", " was turned on.");
                            } else {
                                EventHandler.eventHandle(light, room,false, "Light ", " in room ", " was turned off.");
                            }
                        }
                    }
                }
            }
            if (ExpressionHandler.expressionHandle(event, "||", DOOR_OPEN, DOOR_CLOSED)) {
                // событие от двери
                for (Room room : smartHome.getRooms()) {
                    for (Door door : room.getDoors()) {
                        if (door.getId().equals(event.getObjectId())) {
                            if (ExpressionHandler.expressionHandle(event, DOOR_OPEN)) {
                                EventHandler.eventHandle(door, room,true, "Door "," in room "," was opened.");
                            } else {
                                EventHandler.eventHandle(door, room, false, "Door ", " in room ", " was closed.");
                                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                                if (room.getName().equals("hall")) {
                                    for (Room homeRoom : smartHome.getRooms()) {
                                        for (Light light : homeRoom.getLights()) {
                                            EventHandler.eventHandle(light, false, "Pretent we're sending command ", new SensorCommand(CommandType.LIGHT_OFF, light.getId()));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            event = NextSensorEvent.getNextSensorEvent();
        }

    }
}
