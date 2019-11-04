package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.homeelements.Door;
import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.Room;

import java.util.function.BiFunction;

public class DeviceAction implements Action {
    private Object firstComponent;
    private Object secondComponent;
    private BiFunction<Object, Object, Void> function;

    public DeviceAction(BiFunction<Object, Object, Void> function) {
        this.function = function;
    }

    public void act() {
        function.apply(firstComponent, secondComponent);
    }

    public void updateComponent(Object object) {
        if (object instanceof Room) {
            this.secondComponent = object;
        } else if (object instanceof Door || object instanceof Light) {
            this.firstComponent = object;
        }
    }
}
