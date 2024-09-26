
/**
 * Represents a Node in a linked list.
 * This class is used in Algoritmer og datastrukturer IDATT2101 oving 5 deloppgave 1.
 *
 * @author Jens Christian Aanestad
 * @version 26.09.2023
 * @see LinkedList
 */
public class Node {
  private Object value;
  Node nextNode;
  
  /**
   * Constructor with to parameters.
   *
   * @param value the value of the node.
   * @param nextNode the next node beside this node.
   */
  public Node(Object value, Node nextNode) {
    this.value = value;
    this.nextNode = nextNode;
  }
  
  /**
   * Constructor with one parameter.
   *
   * @param nextNode the next node beside this node.
   */
  public Node(Node nextNode) {
    this.nextNode = nextNode;
  }
  
  /**
   * Returns the value/content of the node.
   *
   * @return the value/content of the node.
   */
  public Object getValue() {
    return value;
  }
  
  /**
   * Sets the value of the node to a new value.
   *
   * @param newValue the new value to the node.
   */
  public void SetValue(Object newValue) {
    this.value = newValue;
  }
  
  /**
   * Returns the node's next node.
   *
   * @return the node's next node.
   */
  public Node findNextNode() {
    return nextNode;
  }
  
  /**
   * Returns true if node has a none null value.
   *
   * @return true if node has a value beside null.
   */
  public boolean hasValue() {
    return value != null;
  }
}
