/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.util.List;

/**
 * Represents a coffee decorator that adds hot water to the coffee.
 */
public class WithHotWater extends CoffeeDecorator {
    /**
     * Constructs a new WithHotWater coffee decorator.
     * @param c The coffee to decorate with hot water.
     * */
    public WithHotWater(Coffee c) {
        super(c);
    }

    /**
     * Retrieves the cost of the coffee with hot water.
     * @return The total cost of the coffee with hot water.
     */
    @Override
    public double getCost() {
        return super.getCost();
    }

    /**
     * Retrieves the list of ingredients for the coffee with hot water.
     * @return The list of ingredients for the coffee with hot water.
     */
    @Override
    public List<String> getIngredients() {
        List<String> ingredients = super.getIngredients();
        ingredients.add("Hot Water");
        return ingredients;
    }

    /**
     * Prints the description of the coffee with hot water.
     * @return The description of the coffee with hot water.
     */
    @Override
    public String printCoffee() {
        return super.printCoffee() + " with hot water";
    }
}
