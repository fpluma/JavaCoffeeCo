/**
 * Author: Fernando Pluma
 * Course: CS160L
 */

import java.util.List;

/**
 * Represents a coffee decorator that adds a flavored syrup to the coffee.
 */
public class WithFlavor extends CoffeeDecorator {

    /**
     * Enumeration of available syrup flavors.
     */
    enum Syrup {
        CARAMEL,
        MOCHA,
        VANILLA
    }
    private Syrup flavor;
    /**
     * Constructs a new WithFlavor coffee decorator.
     * @param c The coffee to decorate.
     * @param s The syrup flavor to add.
     */
    public WithFlavor(Coffee c, Syrup s) {
        super(c);
        flavor = s;
    }

    /**
     * Calculates the cost of the coffee with the added flavored syrup.
     * @return The total cost of the coffee with the flavored syrup.
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.35;
    }

    /**
     * Retrieves the list of ingredients for the coffee with the flavored syrup.
     * @return The list of ingredients for the coffee with the flavored syrup.
     */
    @Override
    public List<String> getIngredients() {
        // Get ingredients from the wrapper Coffee object
        List<String> ingredients = super.getIngredients();
        ingredients.add(flavor.toString() + " Syrup");
        return ingredients;
    }

    /**
     * Prints the description of the coffee with the flavored syrup.
     * @return The description of the coffee with the flavored syrup.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with " + flavor;
    }
}
