/**
 * Represents the source code for Øving 4 IDATT2101 deloppgave 1: Josephus' Problem.
 * 41 Soldiers stands in a circle preferring to kill themselves rather than Roman capture.
 * Every second man is killed until there is one man left who eventually has to commit suicide.
 * Josephus do not want to die, so where should he stand in the circle to be the one man alive?
 * The structure used for solving this problem, is a custom single linked list.
 *
 * @author Jens Christian Aanestad
 * @version 14.09.2023
 */
class JosephusProblem {
  private Soldier head = null;
  private int numberOfSoldiers = 0;
  
  /**
   * Returns the number of soldiers in the linked list.
   *
   * @return number of soldiers in the linked list.
   */
  public int findNumberOfSoldiers() {return numberOfSoldiers;}
  
  /**
   * Returns the head (first soldier in the circle).
   *
   * @return the first soldier.
   */
  public Soldier findHead() {return head;}
  
  /**
   * Places a new soldier first. Note that this method doesn't
   * increment the other soldiers' position number.
   */
  public void placeSoldierFirst() {
    head = new Soldier(1, head);
    numberOfSoldiers++;
  }
  
  /**
   * Places a new soldier behind in the linked list.
   *
   * @param position the position to the soldier.
   */
  public void placeSoldierBehind(int position) {
    if (head != null) {
      Soldier thisSoldier = head;
      while (thisSoldier.nextSoldier != null) thisSoldier = thisSoldier.nextSoldier;
      thisSoldier.nextSoldier = new Soldier(position, null);
    } else {
      head = new Soldier(position, null);
    }
    numberOfSoldiers++;
  }
  
  /**
   * Removes a Soldier from the linked list. This method is special customized
   * with the consideration that the linked list is closed. (The last soldier is
   * linked with the head soldier). That means we can assume that the variable
   * <i>thisSoldier<i/> never can be null. Returns the next soldier after removed
   * the removed soldier.
   *
   * @param soldier the Soldier object to be removed.
   * @return the next soldier after the removed soldier.
   */
  public Soldier remove(Soldier soldier) {
    Soldier preSoldier = null;
    Soldier thisSoldier = head;
    while (thisSoldier != null && thisSoldier!= soldier) {
      preSoldier = thisSoldier;
      thisSoldier = thisSoldier.nextSoldier;
    }
    if (preSoldier != null) {
      preSoldier.nextSoldier = thisSoldier.nextSoldier;
    }
    else {
      // If preSoldier is null, it can ONLY mean that thisSoldier is head.
      head = thisSoldier.nextSoldier;
      // Must increment the number of soldiers with -2 to get right position for the preSoldier.
      // Would've been much easier with a double linked list.
      preSoldier = findSoldierByPosition(numberOfSoldiers-2);
      preSoldier.nextSoldier = head;
    }
    thisSoldier.nextSoldier = null;
    --numberOfSoldiers;
    return preSoldier.nextSoldier;
  }
  
  /**
   * Returns the Soldier at a given position.
   *
   * @param position the position of the soldier.
   * @return the soldier at a given position.
   */
  public Soldier findSoldierByPosition(int position) {
    Soldier thisSoldier = head;
    if (position <= numberOfSoldiers) {
      for (int i = 0; i < position; ++i) thisSoldier = thisSoldier.nextSoldier;
      return thisSoldier;
    }
    else return null;
  }
  
  /**
   * Sets the head to null, and thus resets the linked list.
   */
  public void resetAll() {
    head = null;
    numberOfSoldiers = 0;
  }
  
  /**
   * Finds the last soldier in the linked list. Note that this method is ONLY
   * to be used before the linked list is closed together. Otherwise, it goes in an
   * infinite loop.
   *
   * @return the last soldier.
   */
  public Soldier findLastSoldier() {
    if (head != null) {
      Soldier thisSoldier = head;
      while (thisSoldier.nextSoldier != null) {thisSoldier = thisSoldier.nextSoldier;}
      return thisSoldier;
    } else return null;
  }
  
  /**
   * Creates the linked list of a given number of soldiers, and closes the linked list.
   * @param numberOfSoldiersToCreate the numbers of soldiers to create in the circle.
   */
  public void createSoldiersInCircle(int numberOfSoldiersToCreate) {
    for (int i = 1; i <= numberOfSoldiersToCreate; i++) {
      placeSoldierBehind(i);
    }
    // Closes the circle by letting the last soldier be linked with the head.
    findLastSoldier().nextSoldier = head;
  }
  
  /**
   * Represents the method to solve Josephus' problem, and find the
   * position where one must be placed to be the last man standing.
   *
   * @param n the number of soldiers in circle.
   * @param m the number for the order of killing.
   * @return the position of the last man alive.
   */
  public static int JosephusSolution(int n, int m) {
    JosephusProblem jp = new JosephusProblem();
    jp.createSoldiersInCircle(n);

    Soldier currentSoldier = jp.head;
    int soldierCount = 0;
    while (jp.head.nextSoldier != jp.head) {
      soldierCount++;
      if (soldierCount % m == 0) {
        //System.out.println("Soldier killed at position: " + currentSoldier.findPosition());
        currentSoldier = jp.remove(currentSoldier);
      } else {
        currentSoldier = currentSoldier.nextSoldier;
      }
    }
    return jp.head.findPosition();
  }
  
  /**
   * Main method - the entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    System.out.println("The man alive was at position: " + JosephusSolution(41, 3));
  }
}