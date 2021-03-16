package pva04.builderPattern;

import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Class to generate Meals from. Exercise Builder Pattern.
 */
@Getter
public class Meal {
    private final Burger burger;
    private final Side side;
    private final Drink drink;

    //Accessor disables generated prefixes
    @Accessors(fluent = true) @Getter
    private final boolean hasKetchup;

    @Accessors(fluent = true) @Getter
    private final boolean hasBaconTopping;

    public Meal(Builder builder) {
        this.burger = builder.burger;
        this.side = builder.side;
        this.drink = builder.drink;
        this.hasKetchup = builder.hasKetchup;
        this.hasBaconTopping = builder.hasBaconTopping;
    }

    /**
     * Inner class, that serves as a builder for the Meal class.
     * Inner class for better accessibility
     */
    public static class Builder {
        private final Burger burger;
        private Side side;
        private Drink drink;
        private boolean hasKetchup = false;
        private boolean hasBaconTopping = false;

        /**
         * Constructor takes the initial information for the meal.
         * @param burger may be VEGGIE or MEAT
         */
        public Builder(Meal.Burger burger) {
            this.burger = burger;
        }

        /**
         * Add a side dish to the meal
         * @param side may be SALAD or CHILI_CHEESE_FRIES
         * @return this -> Builder Object with the desired side dish added to it
         * @throws IllegalStateException if SALAD is chosen along with ketchup
         */
        public Builder side(Meal.Side side) throws IllegalStateException{
            if(side == Side.SALAD && this.hasKetchup) throw new IllegalStateException("Salad does not go along with ketchup.");
            this.side = side;
            return this;
        }

        /**
         * Add a drink to the meal
         * @param drink may be COKE or WATER
         * @return this -> Builder Object with the desired drink added to it
         */
        public Builder drink(Meal.Drink drink){
            this.drink = drink;
            return this;
        }

        /**
         * Add a bacon topping to the burger
         * @return this -> Builder Object with the desired drink added to it
         * @throws IllegalStateException if baconTopping is chosen along to a VEGGIE burger
         */
        public Builder baconTopping() throws IllegalStateException
        {
            if(this.burger == Burger.VEGGIE) throw new IllegalStateException("Veggie Burgers may not contain bacon.");
            this.hasBaconTopping = true;
            return this;
        }

        /**
         * Add ketchup to the burger
         * @return this -> Builder Object with ketchup added to it
         * @throws IllegalStateException if ketchup is chosen along with SALAD as a side dish
         */
        public Builder ketchup() throws IllegalStateException
        {
            if(this.side == Side.SALAD) throw new IllegalStateException("Salad does not go along with ketchup.");
            this.hasKetchup = true;
            return this;
        }

        /**
         * Build a Meal Object according to the template built in the Builder class
         * @return Meal consisting of the components specified in the Builder Object
         */
        public Meal build()
        {
            return new Meal(this);
        }
    }

    /**
     * toString method to return all the chosen components
     * @return String representation of a Meal
     */
    @Override
    public String toString() {
        return  "\nYour Meal: " +
                "\nBurger: " + burger +
                "\nSide: " + side +
                "\nDrink: " + drink +
                "\nKetchup: " + hasKetchup +
                "\nBacon Topping: " + hasBaconTopping;
    }

    /**
     * Options for the burger
     */
    public enum Burger {
        VEGGIE,
        MEAT
    }

    /**
     * Options for the side dish
     */
    public enum Side {
        SALAD,
        CHILI_CHEESE_FRIES
    }

    /**
     * Options for the drink
     */
    public enum Drink {
        COKE,
        WATER
    }
}

