package dev.sijiahu.orderingsystem;

import java.util.List;

public class FoodMissingException extends Exception{
    public FoodMissingException(List<String> missingFoodTypes) {
        super(getMissingFoodTypeString(missingFoodTypes));
    }

    private static String getMissingFoodTypeString(List<String> missingFoodTypes) {
        StringBuilder missingString = new StringBuilder();
        int size = missingFoodTypes.size();
        for (int i = 0; i < size; i++) {
            String missingFoodType = missingFoodTypes.get(i);
            if(i >= 1){
                missingString.append(missingFoodType.toLowerCase());
            }else{
                missingString.append(missingFoodType);
            }
            missingString.append(" is missing");

            if(i != size-1){
                missingString.append(", ");
            }
        }
        return missingString.toString();
    }
}
