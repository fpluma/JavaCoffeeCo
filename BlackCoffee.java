/**
 * Author: Fernando Pluma
 * Course: CS160L
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Coffee interface representing a black coffee.
 */
public class BlackCoffee implements Coffee {

    /**
     * Retrieves the cost of the black coffee.
     * @return The cost of the black coffee.
     */
    @Override
    public double getCost() {
        return 1.0;
    }

    /**
     * Retrieves the list of ingredients in the black coffee.
     * @return The list of ingredients in the black coffee.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("Black Coffee");
        return ingredients;
    }

    /**
     * Prints a description of the black coffee.
     * @return A string representation of the black coffee.
     */
    @Override
    public String printCoffee() {
        return "A black coffee";
    }
}