/**
 * Author: Fernando Pluma
 * Course: CS160L
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The Espresso class represents an espresso coffee.
 */
public class Espresso implements Coffee {

    /**
     * Retrieves the cost of the espresso.
     * @return The cost of the espresso.
     */
    @Override
    public double getCost() {
        return 1.75;
    }

    /**
     * Retrieves the list of ingredients in the espresso.
     * @return The list of ingredients in the espresso.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Espresso");
        return ingredients;
    }

    /**
     * Prints a description of the espresso.
     * @return A string representation of the espresso.
     */
    @Override
    public String printCoffee() {
        return "An espresso";
    }
}