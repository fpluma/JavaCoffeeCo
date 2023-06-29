/**
 * Author: Fernando Pluma
 * Course: CS160L
 */

import java.util.List;

/**
 * Represents a coffee decorator that adds whipped cream to the coffee.
 */

public class WithWhippedCream extends CoffeeDecorator {
    /**
     * Constructs a new WithWhippedCream coffee decorator.
     * @param c The coffee to decorate with whipped cream.
     */
    public WithWhippedCream(Coffee c) {
        super(c);
    }

    /**
     * Retrieves the cost of the coffee with whipped cream.
     * @return The total cost of the coffee with whipped cream.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.25;
    }

    /**
     * Retrieves the list of ingredients for the coffee with whipped cream.
     * @return The list of ingredients for the coffee with whipped cream.
     */
    @Override
    public List<String> getIngredients() {
        // Get ingredients from the wrapped Coffee object
        List<String> ingredients = super.getIngredients();
        ingredients.add("Whipped Cream");
        return ingredients;
    }

    /**
     * Prints the description of the coffee with whipped cream.
     * @return The description of the coffee with whipped cream.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with whipped cream";
    }
}