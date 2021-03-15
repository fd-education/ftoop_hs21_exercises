package pva04.BuilderPattern;

import lombok.Getter;

@Getter
public class Meal {
    private final Burger burger;
    private final Side side;
    private final Drink drink;
    private boolean ketchup = false;
    private boolean baconTopping = false;

    public Meal(Builder builder) {
        this.burger = builder.burger;
        this.side = builder.side;
        this.drink = builder.drink;
        this.ketchup = builder.ketchup;
        this.baconTopping = builder.baconTopping;
    }

    public static class Builder {
        private final Burger burger;
        private Side side;
        private Drink drink;
        private boolean ketchup = false;
        private boolean baconTopping = false;

        public Builder(Meal.Burger burger) {
            this.burger = burger;
        }

        public Builder side(Meal.Side side) throws IllegalStateException{
            if(side == Side.SALAD && this.ketchup) throw new IllegalStateException("Salad does not go along with ketchup.");
            this.side = side;
            return this;
        }

        public Builder drink(Meal.Drink drink){
            this.drink = drink;
            return this;
        }

        public Builder ketchup() throws IllegalStateException
        {
            if(this.side == Side.SALAD) throw new IllegalStateException("Salad does not go along with ketchup.");
            this.ketchup = true;
            return this;
        }

        public Builder baconTopping() throws IllegalStateException
        {
            if(this.burger == Burger.VEGGIE) throw new IllegalStateException("Veggie Burgers may not contain bacon.");
            this.baconTopping = true;
            return this;
        }

        public Meal build()
        {
            return new Meal(this);
        }

        @Override
        public String toString() {
            return  "\nYour Meal: " +
                    "\nBurger: " + burger +
                    "\nSide: " + side +
                    "\nDrink: " + drink +
                    "\nKetchup: " + ketchup +
                    "\nBacon Topping: " + baconTopping;
        }
    }

    public enum Burger {
        VEGGIE,
        MEAT
    }

    public enum Side {
        SALAD,
        CHILI_CHEESE_FRIES
    }

    public enum Drink {
        COKE,
        WATER
    }
}

