package dev.sijiahu.orderingsystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EviveOrderingSystem {

    public static void main(String[] args) {
        OrderingSystem orderingSystem = getOrderingSystem();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            System.out.println(handleOrder(orderingSystem, input));
        }
    }

    public static String handleOrder(OrderingSystem orderingSystem, String input) {
        String[] orderArr = input.split(" ");
        String orderTime = orderArr[0];

        try {
            List<Integer> foods;
            if(orderArr.length < 2){
                foods = new ArrayList<>();
            } else {
                foods = Arrays.stream(orderArr[1].split(",")).mapToInt(Integer::parseInt).boxed().toList();
            }
            List<String> meal = orderingSystem.orderFood(orderTime, foods);
            return String.join(", ", meal);

        } catch (Exception e) {
            return "Unable to process: " + e.getMessage();
        }
    }


    public static OrderingSystem getOrderingSystem() {
        OrderingSystem orderingSystem = new OrderingSystem();
        Menu breakfast = new Menu("Breakfast", List.of(
                new Food("Main", "Eggs", false, true),
                new Food("Side", "Toast", false, true),
                new Food("Drink", "Coffee", true, false, "Water", false)
        ));

        Menu lunch = new Menu("Lunch", List.of(
                new Food("Main", "Sandwich", false, true),
                new Food("Side", "Chips", true, true),
                new Food("Drink", "Soda", false, false, "Water", false)
        ));

        Menu dinner = new Menu("Dinner", List.of(
                new Food("Main", "Steak", false, true),
                new Food("Side", "Potatoes", false, true),
                new Food("Drink", "Wine", false, false, "Water", true),
                new Food("Dessert", "Cake", false, true)
        ));
        orderingSystem.addMenu(breakfast);
        orderingSystem.addMenu(lunch);
        orderingSystem.addMenu(dinner);
        return orderingSystem;
    }
}