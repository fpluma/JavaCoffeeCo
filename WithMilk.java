/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.util.List;

/**
 * Represents a coffee decorator that adds milk to the coffee.
 */
public class WithMilk extends CoffeeDecorator {
    /**
     * Constructs a new WithMilk coffee decorator.
     * @param c The coffee to decorate with milk.
     */
    public WithMilk(Coffee c) {
        super(c);
    }

    /**
     Retrieves the cost of the coffee with milk.
     @return The total cost of the coffee with milk.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.55;
    }

    /**
     * Retrieves the list of ingredients for the coffee with milk.
     * @return The list of ingredients for the coffee with milk.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Milk");
        return ingredients;
    }

    /**
     * Prints the description of the coffee with milk.
     * @return The description of the coffee with milk.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with milk";
    }
}