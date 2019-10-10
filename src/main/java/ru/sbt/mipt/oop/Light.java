package ru.sbt.mipt.oop;

public class Light implements HomeComponent {
    // this field show current status of the light (off/on)
    private boolean status;
    private final String id;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.status = isOn;
    }

    public boolean isOn() {
        return status;
    }

    public String getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
