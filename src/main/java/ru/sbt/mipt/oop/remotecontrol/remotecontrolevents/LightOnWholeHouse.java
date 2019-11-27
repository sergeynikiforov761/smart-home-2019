package ru.sbt.mipt.oop.remotecontrol.remotecontrolevents;

import ru.sbt.mipt.oop.homeelements.Light;
import ru.sbt.mipt.oop.homeelements.SmartHome;

public class LightOnWholeHouse implements Command {

    private SmartHome smartHome;

    public LightOnWholeHouse(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setStatus(false);
            }
        });
    }
}
