package dev.sijiahu.orderingsystem;

public class MenuNotFoundException extends Exception{
    public MenuNotFoundException(){
        super("Menu not found");
    }
}
