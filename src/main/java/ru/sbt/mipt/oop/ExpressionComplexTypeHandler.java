package ru.sbt.mipt.oop;

public class ExpressionComplexTypeHandler<T> implements ExpressionHandler{

    private SensorEvent event;
    String operator;
    T firstType;
    T secondType;

    public ExpressionComplexTypeHandler(SensorEvent event, String operator, T firstType, T secondType){
        this.firstType = firstType;
        this.operator = operator;
        this.secondType = secondType;
        this.event = event;
    }

    @Override
    public boolean expressionHandle() {
        // считаем, что по дефолту всегда возвращаем или (||)
        if(operator.equals("&&")){
            return ((event.getType() == firstType) && (event.getType() == secondType));
        }
        else{
            return ((event.getType() == firstType) || (event.getType() == secondType));
        }

    }
}
