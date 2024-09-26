/**
 * Represents a Soldier in the JosephusProblem.
 * A soldier object correlates to a node in a linked list.
 *
 * @author Jens Christian Aanestad.
 * @version 14.09.2023
 * @see JosephusProblem
 */
public class Soldier {
  private int position;
  Soldier nextSoldier;
  
  /**
   * Constructor with to parameters.
   *
   * @param position the position of the soldier in the circle.
   * @param nextSoldier the next soldier beside this soldier.
   */
  public Soldier(int position, Soldier nextSoldier) {
    this.position = position;
    this.nextSoldier = nextSoldier;
  }
  
  /**
   * Constructor with one parameter.
   *
   * @param nextSoldier the next soldier beside this soldier.
   */
  public Soldier(Soldier nextSoldier) {
    this.nextSoldier = nextSoldier;
  }
  
  /**
   * Returns the position of the soldier.
   *
   * @return the position of the soldier.
   */
  public int findPosition() {
    return position;
  }
  
  /**
   * Sets the position of the soldier to a new position.
   *
   * @param newPosition the new position to the soldier.
   */
  public void SetPosition(int newPosition) {
    this.position = newPosition;
  }
  
  /**
   * Increments the position to a soldier with a given number.
   *
   * @param incrementNumber the number to increment the soldier's position with.
   */
  public void incrementPosition(int incrementNumber) {
    this.position += incrementNumber;
  }
  
  /**
   * Returns the next soldier to the soldier.
   *
   * @return the next soldier to the soldier.
   */
  public Soldier findNextSoldier() {
    return nextSoldier;
  }
}
