/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.util.List;

/**
 * The CoffeeDecorator class is an abstract class implementing the Coffee interface.
 * It serves as the base class for decorating coffee objects.
 */
public abstract class CoffeeDecorator implements Coffee {
    private Coffee coffee;

    /**
     * Constructs a CoffeeDecorator object with the specified coffee to be decorated.
     * @param c The coffee object to be decorated.
     */
    public CoffeeDecorator(Coffee c) {
        coffee = c;
    }

    /**
     * Retrieves the cost of the decorated coffee.
     * @return The cost of the decorated coffee.
     */
    public double getCost() {
        return coffee.getCost();
    }

    /**
     * Retrieves the list of ingredients in the decorated coffee.
     * @return The list of ingredients in the decorated coffee.
     */
    public List<String> getIngredients() {
// Get ingredients from the wrapped Coffee object
        List<String> ingredients = coffee.getIngredients();
        return ingredients;
    }

    /**
     * Prints a description of the decorated coffee.
     * @return A string representation of the decorated coffee.
     */
    public String printCoffee() {
        return coffee.printCoffee();
    }
}






