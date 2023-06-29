/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.util.List;

/**
 * Represents a coffee decorator that adds sugar to the coffee.
 */

public class WithSugar extends CoffeeDecorator {
    /**
     * Constructs a new WithSugar coffee decorator.
     * @param c The coffee to decorate with sugar.
     * */
    public WithSugar(Coffee c) {
        super(c);
    }

    /**
     * Retrieves the cost of the coffee with sugar.
     * @return The total cost of the coffee with sugar.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.15;
    }

    /**
     * Retrieves the list of ingredients for the coffee with sugar.
     * @return The list of ingredients for the coffee with sugar.
     */
    @Override
    public List<String> getIngredients() {
        // Get ingredients from the wrapped Coffee object
        List<String> ingredients = super.getIngredients();
        ingredients.add("Sugar");
        return ingredients;
    }

    /**
     Prints the description of the coffee with sugar.
     @return The description of the coffee with sugar.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with sugar";
    }
}