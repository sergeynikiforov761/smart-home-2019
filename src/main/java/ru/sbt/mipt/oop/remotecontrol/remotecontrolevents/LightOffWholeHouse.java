package ru.sbt.mipt.oop.remotecontrol.remotecontrolevents;

import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.SmartHome;

public class LightOffWholeHouse implements Command {

    private SmartHome smartHome;

    public LightOffWholeHouse(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setStatus(true);
            }
        });
    }
}
