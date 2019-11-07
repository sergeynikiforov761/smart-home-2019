package ru.sbt.mipt.oop.remotecontrol;

import ru.sbt.mipt.oop.remotecontrol.remotecontrolevents.EventAction;

import java.util.HashMap;

public class HomeRemoteControl implements RemoteControl {
    private String rcId;
    private HashMap<Buttons, EventAction> remoteControls = new HashMap<>();

    public HomeRemoteControl(String rcId) {
        this.rcId = rcId;
    }


    @Override
    public void onButtonPressed(Buttons buttonCode, String rcId) {
        if (rcId.equals(this.rcId) && remoteControls.containsKey(buttonCode)) {
            remoteControls.get(buttonCode).execute();
        }
    }

    public void addRemoteControl(Buttons button, EventAction eventAction){
        remoteControls.put(button,eventAction);
    }
}
