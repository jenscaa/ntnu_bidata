/**
 * Represents categories.
 *
 * @author Jens Christian Aanestad
 * @version 18.0.2.1
 */
public enum Category {
  FLOOR(1, "Floor"),
  WINDOWS(2, "Windows"),
  DOORS(3, "Doors"),
  LUMBER(4, "Lumber"),
  GARDEN_FURNITURE(5, "Garden Furniture");

  private final int categoryNumber;
  private final String categoryString;

  /**
   * Initializes the categories.
   *
   * @param categoryNumber the category's specified number
   * @param categoryString the category's string representation
   */
  Category(int categoryNumber, String categoryString) {
    this.categoryNumber = categoryNumber;
    this.categoryString = categoryString;
  }
  
  /**
   * Returns the category number.
   *
   * @return categoryNumber
   */
  public int getCategoryNumber() {
    return categoryNumber;
  }
  
  /**
   * Returns the category string.
   *
   * @return categoryString
   */
  public String getCategoryString() {
    return categoryString;
  }
}