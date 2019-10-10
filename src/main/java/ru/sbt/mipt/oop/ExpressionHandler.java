package ru.sbt.mipt.oop;

public class ExpressionHandler {
    public static boolean expressionHandle(SensorEvent event, SensorEventType type){
        return event.getType() == type;
    }

    public static boolean expressionHandle(SensorEvent event, String operator ,SensorEventType typeFirst, SensorEventType typeSecond){
        // считаем, что по дефолту всегда возвращаем или (||)
        if(operator.equals("&&")){
            return ((event.getType() == typeFirst) && (event.getType() == typeSecond));
        }
        else{
            return ((event.getType() == typeFirst) || (event.getType() == typeSecond));
        }

    }
}
