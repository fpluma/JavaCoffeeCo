/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.util.List;

/**
 * The Coffee interface represents a generic coffee.
 */
public interface Coffee {

    /**
     * Retrieves the cost of the coffee.
     * @return The cost of the coffee.
     */
    public double getCost();

    /**
     Retrieves the list of ingredients in the coffee.
     @return The list of ingredients in the coffee.
     */
    public List<String> getIngredients();

    /**
     Prints a description of the coffee.
     @return A string representation of the coffee.
     */
    public String printCoffee();
}