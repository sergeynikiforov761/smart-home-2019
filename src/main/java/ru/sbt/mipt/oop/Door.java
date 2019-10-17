package ru.sbt.mipt.oop;

import java.util.function.Function;

public class Door implements HomeComponent {
    private final String id;
    // this field show current status of the door (closed/open)
    private boolean status;

    public Door(boolean status, String id) {
        this.status = status;
        this.id = id;
    }

    public boolean getStatus(){
        return status;
    }

    public String getId() {
        return id;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
