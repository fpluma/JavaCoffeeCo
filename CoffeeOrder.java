/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The CoffeeOrder class represents a coffee order, containing a list of coffees and an order date.
 */
public class CoffeeOrder {
    private List<Coffee> coffees;
    private LocalDateTime orderDate;

    /**
     * Constructs a CoffeeOrder object with the current order date and an empty list of coffees.
     */
    public CoffeeOrder() {
        coffees = new ArrayList<>();
        orderDate = LocalDateTime.now();
    }

    /**
     * Constructs a CoffeeOrder object with the specified order date and an empty list of coffees.
     * @param orderDate The date of the coffee order.
     */
    public CoffeeOrder(LocalDateTime orderDate) {
        coffees = new ArrayList<>();
        this.orderDate = orderDate;
    }

    /**
     * Adds a coffee to the coffee order.
     * @param c The coffee to be added.
     */
    public void addCoffee(Coffee c) {
        coffees.add(c);
    }

    /**
     * Retrieves the list of coffees in the order.
     * @return The list of coffees in the order.
     */
    public List<Coffee> getCoffees() {
        return coffees;
    }

    /**
     * Retrieves the order date of the coffee order.
     * @return The order date of the coffee order.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Calculates and retrieves the total cost of the coffee order.
     * @return The total cost of the coffee order.
     */
    public double getTotal() {
        double total = 0;
        for (Coffee coffee : coffees) {
            total += coffee.getCost();
        }
        return total;
    }

    /**
     * Prints a receipt for the coffee order, including order details and total cost.
     * @return A string representation of the coffee order receipt.
     */
    public String printOrder() {
        StringBuilder order = new StringBuilder("ORDER RECEIPT\n");
        order.append(String.format("Timestamp: %s%n", orderDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma"))));
        for (int i = 0; i < coffees.size(); i++) {
            Coffee coffee = coffees.get(i);
            order.append(String.format("Item %d: %s - %.2f%n", i + 1, coffee.printCoffee(), coffee.getCost()));
        }
        order.append(String.format("TOTAL = %.2f%n", getTotal()));
        return order.toString();
    }
}