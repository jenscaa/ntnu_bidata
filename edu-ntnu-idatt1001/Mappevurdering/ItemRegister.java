import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represent a registry system for the objects of the class Item.
 *
 * @author Jens Christian Aanestad
 * @version 18.0.2.1
 */
public class ItemRegister {
  private final ArrayList<Item> items;
  
  /**
   * Initializes an empty ArrayList of item objects.
   */
  public ItemRegister() {
    this.items = new ArrayList<Item>();
  }
  
  
  /**
   * Returns all items in the ArrayList items with deep copy.
   *
   * @return items
   */
  public ArrayList<Item> getItems() {
    ArrayList<Item> newItems = new ArrayList<>();
    for (Item item : items) {
      newItems.add(new Item(item));
    }
    return newItems;
  }
  
  /**
   * Returns the specified item in the ArrayList items. If no items
   * exist with the searched itemNumber, then the method returns null.
   *
   * @param itemNumber the specified number for an item
   * @return copy of item
   */
  public Item findItemByItemNumber(String itemNumber) {
    for (Item item : items) {
      if (item.getItemNumber().equals(itemNumber)) {
        return new Item(item);
      }
    }
    return null;
  }
  
  /**
   * Returns the specified item in the ArrayList items. If no items
   * exist with the searched description, then the method returns null.
   *
   * @param description the description belonging to an item
   * @return copy of item
   */
  public Item findItemByDescription(String description) {
    for (Item item : items) {
      if (item.getDescription().equalsIgnoreCase(description)) {
        return new Item(item);
      }
    }
    return null;
  }
  
  
  /**
   * Registers a new item in the ArrayList items.
   *
   * @param itemNumber      a number that represent the item
   * @param description     a description that describes the item
   * @param price           items price in kroner/kr. Can never be a negative number
   * @param brandName       name of brand
   * @param weight          the items weight in kg. Can never be a negative number or zero
   * @param length          the items length in meters. Can never be a negative number or zero
   * @param height          the items height in meters. Can never be a negative number or zero
   * @param color           the color of the item
   * @param numberInStorage items in storage. Can never be a lesser than zero
   * @param categoryNumber  a number that represent the category
   */
  public void registerItem(String itemNumber, String description, int price,
                           String brandName, double weight, double length,
                           double height, String color, int numberInStorage, int categoryNumber) {
    
    Item newItem = new Item(itemNumber, description, price, brandName, weight,
            length, height, color, numberInStorage, categoryNumber);
    // By using the defined equal-method in the class Item, we can check if the arraylist items
    // contains an object with the same item number.
    if (items.contains(newItem)) {
      throw new IllegalArgumentException("The item is already registered");
    }
    items.add(newItem);
  }
  
  /**
   * Registers default items in the ArrayList items.
   */
  public void registerDefaultItems() {
    registerItem("L123", "Square Window", 100,
            "Hunton", 4, 2, 2, "White", 11, 2);
    registerItem("5K23", "Herringbone laminates", 410,
            "Pergo", 4, 10, 0.3, "Black", 210, 1);
    registerItem("D123", "Oak door", 210,
            "Pergo", 6, 0.9, 2.2, "Brown", 21, 3);
    registerItem("P123", "Wooden table", 567,
            "Pergo", 12, 3, 0.7, "White", 9, 4);
    registerItem("E153", "Chair", 234,
            "Egger ", 4, 0.5, 0.8, "Grey", 87, 5);
  }
  
  /**
   * Increases the number of an item in storage for the specified item in items. If the
   * itemNumber does not exist in items then no changes will be made.
   *
   * @param itemNumber item number for the item that will be increased
   * @param increase   how much the number of item in storage will be increased
   */
  public void increaseItemInStorage(String itemNumber, int increase) {
    if (increase < 0) {
      throw new IllegalArgumentException("Number must be a natural number/positive number");
    }
    for (Item item : items) {
      if (item.getItemNumber().equals(itemNumber)) {
        item.setNumberInStorage(item.getNumberInStorage() + increase);
      }
    }
  }
  
  /**
   * Decreases the number of an item in storage for the specified item in items. If the
   * itemNumber does not exist in items then no changes will be made.
   *
   * @param itemNumber item number for the item that will be decreased
   * @param decrease   how much the number of item in storage will be decreased
   */
  public void decreaseItemInStorage(String itemNumber, int decrease) {
    if (decrease < 0) {
      throw new IllegalArgumentException("Number most be a natural number/positive number");
    }
    for (Item item : items) {
      if (item.getItemNumber().equals(itemNumber)) {
        item.setNumberInStorage(item.getNumberInStorage() - decrease);
      }
    }
  }
  
  /**
   * Removes already existing item in the ArrayList items depending on the itemNumber. If the
   * itemNumber does not exist, then no item will be removed.
   *
   * @param itemNumber for the item that will be removed
   */
  public void removeItem(String itemNumber) {
    Item item = findItemByItemNumber(itemNumber);
    items.remove(item);
  }
  
  
  /**
   * Changes the price of a specified item in the ArrayList items. If the itemNumber does
   * not exist in the ArrayList, then no changes will be made.
   *
   * @param itemNumber the item number
   * @param newPrice   the new price
   */
  public void changePriceForItem(String itemNumber, int newPrice) {
    for (Item item : items) {
      if (item.getItemNumber().equals(itemNumber)) {
        item.setPrice(newPrice);
      }
    }
  }
  
  /**
   * Changes the description of a specified item in the ArrayList items. If the itemNumber
   * does not exist in the ArrayList, then no changes will be made.
   *
   * @param itemNumber     the item number
   * @param newDescription the new description
   */
  public void changeDescriptionForItem(String itemNumber, String newDescription) {
    for (Item item : items) {
      if (item.getItemNumber().equals(itemNumber)) {
        item.setDescription(newDescription);
      }
    }
  }
  
  /**
   * Sorts the items by the item's itemNumber.
   */
  public void sortByItemNumber() {
    Comparator<Item> comparator = Comparator.comparing(Item::getItemNumber,
        String.CASE_INSENSITIVE_ORDER);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's description.
   */
  public void sortByDescription() {
    Comparator<Item> comparator = Comparator.comparing(Item::getDescription,
            String.CASE_INSENSITIVE_ORDER);
    items.sort(comparator);
  }
  
  
  /**
   * Sorts the items by the item's price.
   */
  public void sortByPrice() {
    Comparator<Item> comparator = Comparator.comparingInt(Item::getPrice);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's brand name.
   */
  public void sortByBrandName() {
    Comparator<Item> comparator = Comparator.comparing(Item::getBrandName,
        String.CASE_INSENSITIVE_ORDER);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's weight.
   */
  public void sortByWeight() {
    Comparator<Item> comparator = Comparator.comparingDouble(Item::getWeight);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's length.
   */
  public void sortByLength() {
    Comparator<Item> comparator = Comparator.comparingDouble(Item::getLength);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's height.
   */
  public void sortByHeight() {
    Comparator<Item> comparator = Comparator.comparingDouble(Item::getHeight);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's color.
   */
  public void sortByColor() {
    Comparator<Item> comparator = Comparator.comparing(Item::getColor,
        String.CASE_INSENSITIVE_ORDER);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's number in storage.
   */
  public void sortByNumberInStorage() {
    Comparator<Item> comparator = Comparator.comparingInt(Item::getNumberInStorage);
    items.sort(comparator);
  }
  
  /**
   * Sorts the items by the item's categoryNumber.
   */
  public void sortByCategoryNumber() {
    Comparator<Item> comparator = Comparator.comparingInt(Item::getCategoryNumber);
    items.sort(comparator);
  }
  
  /**
   * Returns a string representation of the ItemRegister object.
   *
   * @return representation of ItemRegister object
   */
  @Override
  public String toString() {
    StringBuilder listOfAllItems = new StringBuilder("\n");
    for (Item i : items) {
      listOfAllItems.append(i.toString()).append("\n");
    }
    return listOfAllItems.toString();
  }
}
