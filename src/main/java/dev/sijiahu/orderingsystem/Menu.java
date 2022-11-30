package dev.sijiahu.orderingsystem;

import java.util.List;

public class Menu {

    private final String name;
    private final List<Food> foodList;

    public Menu(String name, List<Food> foodList) {
        this.name = name;
        this.foodList = foodList;
    }

    public Food getFood(int foodId) {
        return foodList.get(foodId - 1);
    }

    public int getFoodListSize() {
        return foodList.size();
    }


    public String getName() {
        return name;
    }
}
