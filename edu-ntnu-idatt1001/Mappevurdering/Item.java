/**
 * Represents an item.
 *
 * @author Jens Christian Aanestad
 * @version 18.0.2.1
 */
public class Item {

  private final String itemNumber;
  private String description;
  private int price;
  private String brandName;
  private final double weight;
  private final double length;
  private final double height;
  private final String color;
  private int numberInStorage;
  private final int categoryNumber;

  /**
   * Initializes an object of the class Item.
   *
   * @param itemNumber      a number that represent the item
   * @param description     a description that describes the item
   * @param price           items price in kroner/kr. Can never be a negative number
   * @param brandName       the item's brand name
   * @param weight          the item's weight in kg. Can never be a negative number or zero
   * @param length          the item's length in meters. Can never be a negative number or zero
   * @param height          the item's height in meters. Can never be a negative number or zero
   * @param color           the color of the item
   * @param numberInStorage items in storage. Can never be a lesser than zero
   * @param categoryNumber  a number that represent the category
   */
  public Item(String itemNumber, String description, int price, String brandName, double weight,
      double length, double height, String color, int numberInStorage, int categoryNumber) {
    
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("Item number cannot be blank");
    }
    
    //Checks if the itemNumber has numbers and letters
    boolean hasLetters = false;
    boolean hasNumbers = false;
    char[] chars = itemNumber.toCharArray();
    for (char i : chars) {
      if (Character.isDigit(i)) {
        hasLetters = true;
      }
      if (Character.isLetter(i)) {
        hasNumbers = true;
      }
    }
    if (!hasLetters || !hasNumbers) {
      throw new IllegalArgumentException("Item number must have numbers AND letters");
    }
    
    if (description.isBlank()) {
      throw new IllegalArgumentException("Description cannot be blank");
    }
    if (brandName.isBlank()) {
      throw new IllegalArgumentException("Brand name cannot be blank");
    }
    if (color.isBlank()) {
      throw new IllegalArgumentException("Color cannot be blank");
    }
    if (price < 0) {
      throw new IllegalArgumentException("Price can't be a negative number");
    }
    if (weight <= 0 || length <= 0 || height <= 0) {
      throw new IllegalArgumentException("Measures can't be a negative number or zero");
    }
    if (numberInStorage < 0) {
      throw new IllegalArgumentException("Numbers in storage can't be a negative number");
    }
    if (categoryNumber < 1 || categoryNumber > Category.values().length) {
      throw new IllegalArgumentException("The category number must be between 1 and "
          + Category.values().length);
    }

    this.itemNumber = itemNumber;
    this.description = description;
    this.price = price;
    this.brandName = brandName;
    this.weight = weight;
    this.length = length;
    this.height = height;
    this.color = color;
    this.numberInStorage = numberInStorage;
    this.categoryNumber = categoryNumber;
  }
  
  /**
   * Initializes an object of the class Item. Is mainly used for deep copying.
   *
   * @param item the object that will be copied
   */
  public Item(Item item) {
    this.itemNumber = item.getItemNumber();
    this.description = item.getDescription();
    this.price = item.getPrice();
    this.brandName = item.getBrandName();
    this.weight = item.getWeight();
    this.length = item.getLength();
    this.height = item.getHeight();
    this.color = item.getColor();
    this.numberInStorage = item.getNumberInStorage();
    this.categoryNumber = item.getCategoryNumber();
  }


  /**
   * Returns the item number.
   *
   * @return itemNumber
   */
  public String getItemNumber() {
    return itemNumber;
  }

  /**
   * Returns the description.
   *
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description to a new description.
   *
   * @param newDescription new description
   */
  public void setDescription(String newDescription) {
    if (newDescription.isBlank()) {
      throw new IllegalArgumentException("The description cannot be blank");
    }
    description = newDescription;
  }

  /**
   * Returns the price.
   *
   * @return price
   */
  public int getPrice() {
    return price;
  }

  /**
   * Sets the price to a new value.
   *
   * @param newPrice new price
   */
  public void setPrice(int newPrice) {
    if (newPrice < 0) {
      throw new IllegalArgumentException("The price cannot be a negative number");
    }
    price = newPrice;
  }

  /**
   * Returns the brand name of the item object.
   *
   * @return brandName
   */
  public String getBrandName() {
    return brandName;
  }
  
  /**
   * Sets the brand name to a new brand name.
   *
   * @param newBrandName new brand name
   */
  public void setBrandName(String newBrandName) {
    brandName = newBrandName;
  }

  /**
   * Returns the weight of the item in kg.
   *
   * @return weight
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Returns the length of the item in meters.
   *
   * @return length
   */
  public double getLength() {
    return length;
  }

  /**
   * Returns the height of the item in meters.
   *
   * @return height
   */
  public double getHeight() {
    return height;
  }

  /**
   * Returns the color of the item.
   *
   * @return color
   */
  public String getColor() {
    return color;
  }

  /**
   * Returns the number of present items in storage.
   *
   * @return numberInStorage
   */
  public int getNumberInStorage() {
    return numberInStorage;
  }

  /**
   * Sets the number in storage for an item to a new value. If the number is below zero,
   * an exception will be thrown.
   *
   * @param newNumberInStorage the new number in storage
   */
  public void setNumberInStorage(int newNumberInStorage) {
    if (newNumberInStorage < 0) {
      throw new IllegalArgumentException("Number of items in storage cannot be a negative number");
    } else {
      numberInStorage = newNumberInStorage;
    }
  }

  /**
   * Returns the number that represents the category of the item object.
   *
   * @return number category
   */
  public int getCategoryNumber() {
    return categoryNumber;
  }

  /**
   * Returns the Category from the categoryNumber.
   *
   * @return Category
   */
  public Category convertToCategory() {
    for (Category i : Category.values()) {
      if (categoryNumber == i.getCategoryNumber()) {
        return i;
      }
    }
    return null;
  }
  
  /**
   * Compares the itemNumber with another objects itemNumber.
   *
   * @param o the object that is being compared to
   * @return if the itemNumbers are equal or not
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item)) {
      return false;
    }
    Item item = (Item) o;
    return this.getItemNumber().equals(item.getItemNumber());
  }
  
  /**
   * Returns a String that represent the item registry.
   *
   * @return object
   */
  @Override
  public String toString() {
    return "Item number: " + itemNumber + ",  Description: " + description + ",  Price: " + price
        + " kr,  Brand name: " + brandName + ",  Weight: " + weight + " kg,  Length: " + length
        + " m,  Height: " + height + " m,  Color: " + color + ",  Number in storage: "
        + numberInStorage + ",  Category: (" + categoryNumber + ") "
        + convertToCategory().getCategoryString();
  }
}