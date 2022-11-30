package dev.sijiahu.orderingsystem;

public class ExtraFoodException extends Exception{
    public ExtraFoodException(String meg){
        super(meg + " cannot be ordered more than once");
    }
}
