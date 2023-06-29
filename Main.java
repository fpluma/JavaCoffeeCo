/**
 @author Fernando Pluma
 Course: CS160L Summer 2023
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * The Main class represents the main entry point of the Java Coffee Co. application.
 */
public class Main {
    private static Map<String, Integer> inventory = new TreeMap<>();
    private static List<CoffeeOrder> orders = new ArrayList<>();
    private static String logFile = "OrderLog.txt";
    private static String inventoryFile = "Inventory.txt";
    private static Coffee weeklySpecial;

    /**
     * The main method that executes the Java Coffee Co. application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        loadInventory(inventoryFile);

        System.out.println("Welcome to Java Coffee Co.!");
        try (Scanner input = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                System.out.println("Main Menu:");
                System.out.println("1. New Order");
                System.out.println("2. Reload Inventory");
                System.out.println("3. Update Inventory");
                System.out.println("4. Update Order Log");
                System.out.println("5. Generate Weekly Special");
                System.out.println("6. Exit Application");
                System.out.println("Please enter your choice:");

                int choice;
                while (true) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        choice = input.nextInt();
                        input.nextLine();
                        if (choice >= 1 && choice <= 6) {
                            break;
                        } else {
                            System.out.println("Please enter a valid option.");
                        }
                    }
                }

                switch (choice) {
                    case 1:
                        CoffeeOrder order = buildOrder();
                        orders.add(order);
                        System.out.println(order.printOrder());
                        break;
                    case 2:
                        loadInventory(inventoryFile);
                        printInventory();
                        break;
                    case 3:
                        writeInventory(inventoryFile);
                        break;
                    case 4:
                        writeOrderLog(logFile);
                        break;
                    case 5:
                        Coffee weeklySpecial = generateWeeklySpecial();
                        System.out.println("Weekly Special:");
                        System.out.println(weeklySpecial.printCoffee());
                        break;
                    case 6:
                        exit = true;
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        if (orders.size() > 0) {
            writeOrderLog(logFile);
        }
    }


    /**
     * Builds a coffee order by interacting with the user.
     * @return The constructed coffee order.
     */
    private static CoffeeOrder buildOrder() {
        CoffeeOrder order = new CoffeeOrder();
        try {
            Scanner input = new Scanner(System.in);
            boolean addCoffee = true;
            while (addCoffee) {
                // Prompt user to select base coffee type
                System.out.println("Select coffee type:");
                System.out.println("\t1. Black Coffee");
                System.out.println("\t2. Espresso");
                System.out.println("\t3. Weekly Special");
                Coffee coffee;

                int option = 0;
                while (option < 1 || option > 4) {
                    if (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    } else {
                        option = input.nextInt();
                        input.nextLine();
                        if (option < 1 || option > 4)
                            System.out.println("Please enter a valid option.");
                    }
                }

                switch (option) {
                    case 1:
                        coffee = new BlackCoffee();
                        break;
                    case 2:
                        coffee = new Espresso();
                        break;
                    case 3:
                        if (weeklySpecial == null) {
                            System.out.println("Weekly special not available.");
                            coffee = null;
                        } else {
                            coffee = weeklySpecial;
                        }
                        break;
                    case 4:
                        System.out.println("Generating weekly special...");
                        generateWeeklySpecial();
                        if (weeklySpecial == null) {
                            System.out.println("Weekly special not available.");
                            coffee = null;
                        } else {
                            coffee = weeklySpecial;
                        }
                        break;
                    default:
                        coffee = new BlackCoffee();  // Default to Black Coffee
                        break;
                }

                // Prompt user for any customizations
                while (option != 0) {
                    System.out.println(String.format("Coffee brewing: %s.", coffee.printCoffee()));
                    System.out.println("Would you like to add anything to your coffee?");
                    System.out.println("\t1. Flavored Syrup");
                    System.out.println("\t2. Hot Water");
                    System.out.println("\t3. Milk");
                    System.out.println("\t4. Sugar");
                    System.out.println("\t5. Whipped Cream");
                    System.out.println("\t0. NO - Finish Coffee");

                    while (!input.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        input.nextLine();
                    }
                    option = input.nextInt();
                    input.nextLine();
                    coffee = switch (option) {
                        case 1 -> {
                            System.out.println("Please select a flavor:");
                            for (WithFlavor.Syrup flavor : WithFlavor.Syrup.values()) {
                                System.out.println("\t" + String.format("%d. %s", flavor.ordinal() + 1, flavor));
                            }
                            int max = WithFlavor.Syrup.values().length;
                            option = 0;
                            while (option < 1 || option > max) {
                                if (!input.hasNextInt()) {
                                    System.out.println("Please enter a valid number.");
                                    input.nextLine();
                                } else {
                                    option = input.nextInt();
                                    if (option < 1 || option > max)
                                        System.out.println("Please enter a valid option.");
                                }
                            }
                            input.nextLine();
                            WithFlavor.Syrup flavor = WithFlavor.Syrup.values()[option - 1];
                            option = 1;
                            yield new WithFlavor(coffee, flavor);
                        }
                        case 2 -> new WithHotWater(coffee);
                        case 3 -> new WithMilk(coffee);
                        case 4 -> new WithSugar(coffee);
                        case 5 -> new WithWhippedCream(coffee);
                        default -> {
                            if (option != 0)
                                System.out.println("Please enter valid option.");
                            yield coffee;
                        }
                    };
                }

                // Check if ingredients exist in the inventory before adding the coffee
                boolean allIngredientsAvailable = true;
                for (String ingredient : coffee.getIngredients()) {
                    if (!isInInventory(ingredient)) {
                        System.out.println("Sorry, no " + ingredient + " is left in the inventory.");
                        allIngredientsAvailable = false;
                        break;
                    }
                }

                if (allIngredientsAvailable) {
                    // Reduce the quantity of ingredients in the inventory
                    for (String ingredient : coffee.getIngredients()) {
                        updateInventory(ingredient);
                    }
                    order.addCoffee(coffee);
                }

                System.out.println("Would you like to order another coffee (Y or N)?");
                String yn = input.nextLine();
                while (!(yn.equalsIgnoreCase("N") || yn.equalsIgnoreCase("Y"))) {
                    System.out.println("Please enter Y or N.");
                    yn = input.nextLine();
                }
                addCoffee = !yn.equalsIgnoreCase("N");
            }
        } catch (Exception e) {
            System.out.println("Error building order: " + e.getMessage());
        }
        return order;
    }

    /**
     * Loads the inventory data from a file.
     * @param filePath The path of the inventory file.
     */
    private static void loadInventory(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" = ");
                if (parts.length == 2) {
                    String ingredient = parts[0].trim();
                    int quantity = Integer.parseInt(parts[1].trim());
                    inventory.put(ingredient, quantity);
                }
            }
        } catch (Exception e) {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

    /**
     * Prints the current inventory.
     */
    private static void printInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    /**
     * Writes the current inventory to a file.
     * @param filePath The path of the inventory file.
     */
    private static void writeInventory(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
                writer.write(entry.getKey() + " = " + entry.getValue());
                writer.newLine();
            }
            System.out.println("Inventory updated and written successfully.");
        } catch (Exception e) {
            System.out.println("Error writing inventory: " + e.getMessage());
        }
    }

    /**
     * Updates the inventory by reducing the quantity of a specific ingredient.
     * @param ingredient The ingredient to update.
     */
    private static void updateInventory(String ingredient) {
        int quantity = inventory.get(ingredient);
        inventory.put(ingredient, quantity - 1);
    }

    /**
     * Writes the order log to a file.
     * @param filePath The path of the order log file.
     */
    private static void writeOrderLog(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (CoffeeOrder order : orders) {
                writer.write(order.printOrder());
                writer.newLine();
            }
            orders.clear();
            System.out.println("Order log updated and written successfully.");
        } catch (Exception e) {
            System.out.println("Error writing order log: " + e.getMessage());
        }
    }

    /**
     * Checks if a specific ingredient is available in the inventory.
     * @param ingredient The ingredient to check.
     * @return {@code true} if the ingredient is available, {@code false} otherwise.
     */
    private static boolean isInInventory(String ingredient) {
        return inventory.containsKey(ingredient) && inventory.get(ingredient) > 0;
    }


    /**
     Generates a random coffee with a random number of decorations.
     @return The randomly generated coffee which will be used as the weekly special.
     */
    private static Coffee generateWeeklySpecial() {
        Random rand = new Random();
        int coffeeType = rand.nextInt(2); // 0 for Black Coffee, 1 for Espresso
        Coffee coffee;
        if (coffeeType == 1) {
            coffee = new Espresso();
        } else {
            coffee = new BlackCoffee();
        }
        int numDecorations = rand.nextInt(4); // Randomly choose number of decorations (0-3)
        for (int i = 0; i < numDecorations; i++) {
            int decorationType = rand.nextInt(4); // Randomly choose decoration type
            switch (decorationType) {
                case 0:
                    coffee = new WithFlavor(coffee, WithFlavor.Syrup.values()[rand.nextInt(WithFlavor.Syrup.values().length)]);
                    break;
                case 1:
                    coffee = new WithHotWater(coffee);
                    break;
                case 2:
                    coffee = new WithMilk(coffee);
                    break;
                case 3:
                    coffee = new WithSugar(coffee);
                    break;
                case 4:
                    coffee = new WithWhippedCream(coffee);
                    break;
            }
        }
        weeklySpecial = coffee;
        return coffee;
    }
}