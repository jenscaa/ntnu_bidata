import java.util.Scanner;

/**
 * Represents a user interface.
 *
 * @author Jens Chrisitan Aanestad
 * @version 18.0.2.1
 */
public class UserInterface {
  
  private static final int PRINT_ALL_ITEMS = 1;
  private static final int SEARCH_ITEM = 2;
  private static final int REGISTER_NEW_ITEM = 3;
  private static final int INCREASE_ITEM_IN_STORAGE = 4;
  private static final int DECREASE_ITEM_IN_STORAGE = 5;
  private static final int REMOVE_ITEM = 6;
  private static final int CHANGE_PRICE_FOR_ITEM = 7;
  private static final int CHANGE_DESCRIPTION_FOR_ITEM = 8;
  private static final int SORT_ITEMS = 9;
  private static final int QUIT = 10;
  private static Scanner scanner;
  
  
  /**
   * Displays the menu and returns an Integer from the user.
   *
   * @return operation
   */
  public int showMenu() {
    scanner = new Scanner(System.in);
    int operation = 0;
    System.out.println("""
            \n********** Warehouse Application **********
            
            Choose operations:
                
                (1): Print all items
                (2): Search for item
                (3): Register new item
                (4): Increase item in storage
                (5): Decrease item in storage
                (6): Remove item
                (7): Change price for item
                (8): Change description for item
                (9): Sort items
               (10): Exit
            """);
    
    if (scanner.hasNextInt()) {
      operation = Integer.parseInt(scanner.nextLine().replace(" ", ""));
    }
    return operation;
  }
  
  /**
   * Contains the main loop of this user interface.
   */
  public void run() {
    scanner = new Scanner(System.in);
    
    ItemRegister register = new ItemRegister();
    register.registerDefaultItems();
    
    boolean finished = false;
    while (!finished) {
      int operation = this.showMenu();
      switch (operation) {
        
        case PRINT_ALL_ITEMS -> System.out.println(register);
        
        case SEARCH_ITEM -> {
          System.out.println("\nWrite the item number or the description "
                  + "for the item you will find: ");
          String searchString = scanner.nextLine();
          if (register.findItemByItemNumber(searchString) != null) {
            System.out.println("\n" + register.findItemByItemNumber(searchString));
          }
          if (register.findItemByDescription(searchString) != null) {
            System.out.println("\n" + register.findItemByDescription(searchString));
          }
          if (register.findItemByItemNumber(searchString) == null
                  && register.findItemByDescription(searchString) == null) {
            System.out.println("\nItem does not exist!");
          }
        }
        
        case REGISTER_NEW_ITEM -> {
          try {
            System.out.println("\nType in new itemNumber: ");
            final String newItemNumber = scanner.nextLine();
            System.out.println("Type in description for the item: ");
            final String newDescription = scanner.nextLine();
            System.out.println("Type in the price in kr: ");
            final int newPrice = Integer.parseInt(scanner.nextLine());
            System.out.println("Type in brand name: ");
            final String newBrandName = scanner.nextLine();
            System.out.println("Type in the item's weight in kg: ");
            final double newWeight = Double.parseDouble(scanner.nextLine());
            System.out.println("Type in the item's length in meters: ");
            final double newLength = Double.parseDouble(scanner.nextLine());
            System.out.println("Type in the item's height in meters: ");
            final double newHeight = Double.parseDouble(scanner.nextLine());
            System.out.println("Type in the item's color: ");
            final String newColor = scanner.nextLine();
            System.out.println("Type in how many of the item that are in storage: ");
            final int inStorage = Integer.parseInt(scanner.nextLine());
            System.out.println("Type in the item's category number: ");
            // Prints the examples of valid categories
            String example = "\nExample:\n";
            for (int i = 0; i < Category.values().length; i++) {
              example += "    (" + (i + 1) + "): "
                      + Category.values()[i].getCategoryString() + "\n";
            }
            System.out.println(example);
            final int newCategory = Integer.parseInt(scanner.nextLine());
            register.registerItem(newItemNumber, newDescription, newPrice, newBrandName,
                    newWeight, newLength, newHeight, newColor, inStorage, newCategory);
            System.out.println("\nThe item has been registered!\n");
            System.out.println(register);
          } catch (NumberFormatException n) {
            System.out.println("Can't add item because of invalid argument in input section!");
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
        
        case INCREASE_ITEM_IN_STORAGE -> {
          try {
            System.out.println("\nType in the item number for the "
                    + "item which is being increased in storage:");
            String itemNumber = scanner.nextLine();
            if (register.findItemByItemNumber(itemNumber) == null) {
              throw new IllegalArgumentException("Could not preform the task because the item "
                      + "number does not exist in the registry!");
            }
            System.out.println("How much is the item increased with? (The number in storage is "
                    + register.findItemByItemNumber(itemNumber).getNumberInStorage() + "):");
            int increase = Integer.parseInt(scanner.nextLine());
            register.increaseItemInStorage(itemNumber, increase);
            System.out.println("Numbers in storage updated!\n ");
            System.out.println(register.findItemByItemNumber(itemNumber));
          } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
          }
        }
        
        case DECREASE_ITEM_IN_STORAGE -> {
          try {
            System.out.println("\nType in the item number for the "
                    + "item which is being decreased in storage:");
            String itemNumber = scanner.nextLine();
            if (register.findItemByItemNumber(itemNumber) == null) {
              throw new IllegalArgumentException("Could not preform the task because the item \n"
                      + "number does not exist in the registry!");
            }
            System.out.println("How much is the item decreased with? (The number in storage is "
                    + register.findItemByItemNumber(itemNumber).getNumberInStorage() + "):");
            int decrease = Integer.parseInt(scanner.nextLine());
            register.decreaseItemInStorage(itemNumber, decrease);
            System.out.println("Numbers in storage updated!\n ");
            System.out.println(register.findItemByItemNumber(itemNumber));
          } catch (IllegalArgumentException i) {
            System.out.println(i.getMessage());
          }
        }
        
        case REMOVE_ITEM -> {
          System.out.println("\nType in the item number for the item you wish to remove: ");
          String itemNumber = scanner.nextLine();
          if (register.findItemByItemNumber(itemNumber) == null) {
            System.out.println("Could not preform the task because the item does not exist!");
          } else {
            register.removeItem(itemNumber);
            System.out.println("Item removed!");
            System.out.println(register);
          }
        }
        
        case CHANGE_PRICE_FOR_ITEM -> {
          try {
            System.out.println("\nType in the item number for the item you wish to change price:");
            String itemNumber = scanner.nextLine();
            if (register.findItemByItemNumber(itemNumber) == null) {
              throw new IllegalArgumentException("Could not preform the task "
                      + "because the item does not exist!");
            }
            System.out.println("Type in the new price for the item: ");
            int newPrice = Integer.parseInt(scanner.nextLine());
            register.changePriceForItem(itemNumber, newPrice);
            System.out.println("Price updated!\n");
            System.out.println(register.findItemByItemNumber(itemNumber));
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
        
        case CHANGE_DESCRIPTION_FOR_ITEM -> {
          try {
            System.out.println("\nType in the item number for the "
                    + "item you wish to change description: ");
            String itemNumber = scanner.nextLine();
            if (register.findItemByItemNumber(itemNumber) == null) {
              throw new IllegalArgumentException("Could not preform the task "
                      + "because the item does not exist!");
            }
            System.out.println("Type in the new description for the item: ");
            String description = scanner.nextLine();
            register.changeDescriptionForItem(itemNumber, description);
            System.out.println("Description updated!\n");
            System.out.println(register.findItemByItemNumber(itemNumber));
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }
        }
        
        case SORT_ITEMS -> {
          boolean isSorted = true;
          System.out.println("""
                  Sort by following:
                     (1): Item number
                     (2): Description
                     (3): Price
                     (4): Brand name
                     (5): Weight
                     (6): Length
                     (7): Height
                     (8): Color
                     (9): Number in storage
                    (10): Category number""");
          String answer = scanner.nextLine();
          switch (answer) {
            case "1" -> register.sortByItemNumber();
            case "2" -> register.sortByDescription();
            case "3" -> register.sortByPrice();
            case "4" -> register.sortByBrandName();
            case "5" -> register.sortByWeight();
            case "6" -> register.sortByLength();
            case "7" -> register.sortByHeight();
            case "8" -> register.sortByColor();
            case "9" -> register.sortByNumberInStorage();
            case "10" -> register.sortByCategoryNumber();
            default -> {
              System.out.println("\nUnrecognized sort selected!");
              isSorted = false;
            }
          }
          if (isSorted) {
            System.out.println("\nItems sorted!");
            System.out.println(register);
          }
        }
        
        case QUIT -> {
          finished = true;
          System.out.println("Exiting the program...");
        }
        
        default -> System.out.println("Unrecognized menu selected");
      }
    }
    scanner.close();
  }
  
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.run();
  }
}