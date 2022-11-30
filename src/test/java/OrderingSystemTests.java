import dev.sijiahu.orderingsystem.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OrderingSystemTests {
    static OrderingSystem orderingSystem;

    @BeforeAll
    static void setup() {
        orderingSystem = EviveOrderingSystem.getOrderingSystem();
    }

    @Test
    void testMenuNotFound() {
        Exception brunchNotFound = assertThrows(MenuNotFoundException.class, () -> orderingSystem.orderFood("Brunch", new ArrayList<>()));
        assertEquals("Menu not found", brunchNotFound.getMessage());
        Exception noMenu = assertThrows(MenuNotFoundException.class, () -> orderingSystem.orderFood("", new ArrayList<>()));
        assertEquals("Menu not found", noMenu.getMessage());
    }

    @Test
    void testFoodMissing() {
        Exception sideMissing = assertThrows(FoodMissingException.class, () -> orderingSystem.orderFood("Breakfast", List.of(1)));
        assertEquals("Side is missing", sideMissing.getMessage());

        Exception mainSideMissing = assertThrows(FoodMissingException.class, () -> orderingSystem.orderFood("Lunch", new ArrayList<>()));
        assertEquals("Main is missing, side is missing", mainSideMissing.getMessage());

        Exception dessertMissing = assertThrows(FoodMissingException.class, () -> orderingSystem.orderFood("Dinner", List.of(1, 2, 3)));
        assertEquals("Dessert is missing", dessertMissing.getMessage());

    }

    @Test
    void testExtraFood() {
        Exception extraSandwiches = assertThrows(ExtraFoodException.class, () -> orderingSystem.orderFood("Lunch", List.of(1, 1, 2, 3)));
        assertEquals("Sandwich cannot be ordered more than once", extraSandwiches.getMessage());
        Exception extraPotatoes = assertThrows(ExtraFoodException.class, () -> orderingSystem.orderFood("Dinner", List.of(1, 2, 2, 3, 4)));
        assertEquals("Potatoes cannot be ordered more than once", extraPotatoes.getMessage());
    }

    @Test
    void testSimple() {
        assertDoesNotThrow(() -> {
            assertEquals(List.of("Eggs", "Toast", "Coffee"), orderingSystem.orderFood("Breakfast", List.of(1, 2, 3)));
            assertEquals(List.of("Eggs", "Toast", "Coffee"), orderingSystem.orderFood("Breakfast", List.of(2, 3, 1)));

            assertEquals(List.of("Sandwich", "Chips", "Soda"), orderingSystem.orderFood("Lunch", List.of(1, 2, 3)));


        });
    }

    @Test
    void testMultiple() {
        assertDoesNotThrow(() -> {
            assertEquals(List.of("Eggs", "Toast", "Coffee(3)"), orderingSystem.orderFood("Breakfast", List.of(1, 2, 3, 3, 3)));
            assertEquals(List.of("Sandwich", "Chips(2)", "Water"), orderingSystem.orderFood("Lunch", List.of(1, 2, 2)));
        });
    }

    @Test
    void testAlternative() {
        assertDoesNotThrow(() -> {
            assertEquals(List.of("Sandwich", "Chips", "Water"), orderingSystem.orderFood("Lunch", List.of(1, 2)));
        });
    }

    @Test
    void testForcedAlternative() {
        assertDoesNotThrow(() -> {
            assertEquals(List.of("Steak", "Potatoes", "Wine", "Water", "Cake"), orderingSystem.orderFood("Dinner", List.of(1, 2, 3, 4)));
            assertEquals(List.of("Steak", "Potatoes", "Water", "Cake"), orderingSystem.orderFood("Dinner", List.of(1, 2, 4)), "water shows up twice");
        });
    }

    @Test
    void testInputAndOutput() {
        assertEquals("Eggs, Toast, Coffee(3)", EviveOrderingSystem.handleOrder(orderingSystem, "Breakfast 1,2,3,3,3"));

        assertEquals("Unable to process: Main is missing, side is missing", EviveOrderingSystem.handleOrder(orderingSystem, "Lunch"));

    }
}
