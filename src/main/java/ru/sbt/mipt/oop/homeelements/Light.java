package ru.sbt.mipt.oop.homeelements;


import ru.sbt.mipt.oop.HomeComponent;
import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.Actionable;


public class Light implements HomeComponent, Actionable {
    private final String id;
    // this field show current status of the light (off/on)
    private boolean status;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.status = isOn;
    }

    public String getId() {
        return id;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    @Override
    public void execute(Action action) {
        action.act(this);
    }
}
