package ru.sbt.mipt.oop;

public class ExpressionStringHandler implements ExpressionHandler {

    private Room room;
    private String place;

    public ExpressionStringHandler(Room room, String place){
        this.room = room;
        this.place = place;
    }

    @Override
    public boolean expressionHandle() {
        return room.getName().equals(place);
    }
}
