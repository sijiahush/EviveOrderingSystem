package dev.sijiahu.orderingsystem;

import java.util.*;

public class OrderingSystem {
    private final Map<String, Menu> menuList = new HashMap<>();

    public void addMenu(Menu menu){
        menuList.put(menu.getName(), menu);
    }

    public List<String> orderFood(String orderTime, List<Integer> foodIdsList) throws ExtraFoodException, FoodMissingException, MenuNotFoundException {
        if(!menuList.containsKey(orderTime)){
            throw new MenuNotFoundException();
        }
        Menu menu = menuList.get(orderTime);
        ArrayList<String> foodList = new ArrayList<>();
        
       int foodListSize = menu.getFoodListSize();

       List<String> missingFoodTypes = new ArrayList<>();

        for (int i = 1; i <= foodListSize; i++) {
            Food food = menu.getFood(i);

            int occurrence = Collections.frequency(foodIdsList, i);

            if (occurrence == 0){
                if(food.isRequired()){
                    missingFoodTypes.add(food.getType());
                    continue;
                }else if(food.getAlternate() != null){
                    foodList.add(food.getAlternate());
                }
            } else{
                if (occurrence == 1) {
                    foodList.add(food.getName());
                } else if (food.isMultipleAllowed()) {
                    foodList.add(food.getName() + "(" + occurrence + ")");
                }else{
                    throw new ExtraFoodException(food.getName());
                }

                if(food.isForceAlternative()){
                    foodList.add(food.getAlternate());
                }
            }
        }

        if (!missingFoodTypes.isEmpty()){
            throw new FoodMissingException(missingFoodTypes);
        }

        return foodList;
    }
}
