# JavaCoffeeCo
CS160L Java Coffee Co. Application. This application allows you to place coffee orders, manage/update inventory, generate weekly specials, and maintain an order log.

## Instructions
1. Install Java Development Kit (JDK) installed on your system.
2. Download the source code files from the repository.
3. Open a terminal or command prompt and navigate to the directory containing the source code files.

## Compiling and Running the Application
4. Compile the Java source files using the command: javac Main.java
5. Run the application using the following command: java Main
6. The application will display a main menu with 6 different options. Select the provided menu options to interact with the application.

## Files
This application consists of 12 different files:
- 'Main.java': This file containts the main method and handles the user interface and interacition. This is the main entry point of the application.
- 'Coffee.java': This file provides methods for printing coffee details and retrieving its ingredients. This file defines the Coffee class, which represents a base coffee type.
- 'CoffeeDecorator.java': This files defines the CoffeeDecorator class. This file includes methods for itional features.decorating coffe objects with additional features.
- 'BlackCoffee.java': This This file extends the Coffee class and represents a black coffee type. This file inherits the properties and methods of the base class.
- 'Espresso.java': This file also extends the Coffee class and represents an esspresso coffee type. This file inherits the properties and methods of the base class.
- 'WithFlavor.java', 'WithHotWater.java', 'WithMilk.java', 'WithSugar.java', 'WithWhippedCream.java': All 5 of these files represent different types of coffee customizations. Each file extends the Coffee class and provides additional functionality depending on the customization.
- 'Inventory.txt': This file contains the available quantities of various ingredients.
- 'OrderLog.txt': This file keeps a log of each order placed.
