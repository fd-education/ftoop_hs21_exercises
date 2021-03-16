package pva04.builderPattern;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {
    private static Meal healthyMeal;
    private static Meal unhealthyMeal;

    private static final String EXPECTED_HEALTHY_STRING = "" +
            "\nYour Meal: " +
            "\nBurger: VEGGIE" +
            "\nSide: SALAD" +
            "\nDrink: WATER" +
            "\nKetchup: false" +
            "\nBacon Topping: false";

    private static final String EXPECTED_UNHEALTHY_STRING = "" +
            "\nYour Meal: " +
            "\nBurger: MEAT" +
            "\nSide: CHILI_CHEESE_FRIES" +
            "\nDrink: COKE" +
            "\nKetchup: true" +
            "\nBacon Topping: true";

    @BeforeAll
    public static void initTestFixture(){
        healthyMeal = new Meal.Builder(Meal.Burger.VEGGIE)
                .side(Meal.Side.SALAD)
                .drink(Meal.Drink.WATER)
                .build();

        unhealthyMeal = new Meal.Builder(Meal.Burger.MEAT)
                .side(Meal.Side.CHILI_CHEESE_FRIES)
                .drink(Meal.Drink.COKE)
                .baconTopping()
                .ketchup()
                .build();
    }

    @Test
    public void builderHealthyTest(){
        assertEquals(Meal.Burger.VEGGIE, healthyMeal.getBurger());
        assertEquals(Meal.Side.SALAD, healthyMeal.getSide());
        assertEquals(Meal.Drink.WATER, healthyMeal.getDrink());
        assertFalse(healthyMeal.hasKetchup());
        assertFalse(healthyMeal.hasBaconTopping());
    }

    @Test
    public void builderUnhealthyTest(){
        assertEquals(Meal.Burger.MEAT, unhealthyMeal.getBurger());
        assertEquals(Meal.Side.CHILI_CHEESE_FRIES, unhealthyMeal.getSide());
        assertEquals(Meal.Drink.COKE, unhealthyMeal.getDrink());
        assertTrue(unhealthyMeal.hasKetchup());
        assertTrue(unhealthyMeal.hasBaconTopping());
    }

    @Test
    public void toStringTest(){
        assertEquals(EXPECTED_HEALTHY_STRING, healthyMeal.toString());
        assertEquals(EXPECTED_UNHEALTHY_STRING, unhealthyMeal.toString());
    }

    @Test
    public void veggieBaconTest(){
        assertThrows(IllegalStateException.class, () -> new Meal.Builder(Meal.Burger.VEGGIE).baconTopping().build());
    }

    @Test
    public void saladKetchupTest(){
        assertThrows(IllegalStateException.class, () -> new Meal.Builder(Meal.Burger.VEGGIE).side(Meal.Side.SALAD).ketchup().build());
    }
}
