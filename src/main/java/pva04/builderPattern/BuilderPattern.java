package pva04.builderPattern;

public final class BuilderPattern {

    public static void main(String[] args) {

        /* Der folgende Code soll ohne Aenderungen lauffähig sein.*/
        Meal healthyMeal = new Meal.Builder(Meal.Burger.VEGGIE)
                .side(Meal.Side.SALAD)
                .drink(Meal.Drink.WATER)
                .build();

        Meal unhealthyMeal = new Meal.Builder(Meal.Burger.MEAT)
                .side(Meal.Side.CHILI_CHEESE_FRIES)
                .drink(Meal.Drink.COKE)
                .baconTopping()
                .ketchup()
                .build();
        /* [...] Remaining code omitted. */

        System.out.println(healthyMeal.toString());
        System.out.println(unhealthyMeal.toString());
    }
}