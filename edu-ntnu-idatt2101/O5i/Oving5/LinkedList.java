/**
 * Represents a linked list using nodes.
 * This class is used in Algoritmer og datastrukturer IDATT2101 oving 5 deloppgave 1.
 *
 * @author Jens Christian Aanestad
 * @version 26.09.2023
 * @see Node
 */
public class LinkedList {
  Node head = new Node(null);
  private int size = 0;
  
  /**
   * Returns the head node.
   *
   * @return the head node.
   */
  public Node getHead() {
    return head;
  }
  
  /**
   * Returns the size of the linked list.
   *
   * @return the size of the linked list.
   */
  public int getSize() {
    return size;
  }
  
  /**
   * Places a new node at the head position/start position.
   * The previous head will be the new node's next value.
   */
  public void placeNewNodeFirst() {
    head = new Node(head);
  }
  
  /**
   * Places a new node at the head position/start position.
   * The previous head will be the new node's next value.
   *
   * @param value an object to pass to this node.
   */
  public void placeNodeFirst(Object value) {
    head = new Node(value, head);
    ++size;
  }
  
  /**
   * Places a node the furthest away from the head node/in behind.
   * If it does not exist any node, then the new node will be head.
   *
   * @param value an object to pass to this node.
   */
  public void placeNodeBehind(Object value) {
    if (head != null) {
      Node currentNode = head;
      while (currentNode.nextNode != null) currentNode = currentNode.nextNode;
      currentNode.nextNode = new Node(value, null);
    } else {
      head = new Node(value, null);
    }
    ++size;
  }
  
  /**
   * Removes a node from the linked list.
   *
   * @param node the node to remove.
   * @return the node that was removed.
   */
  public Node removeNode(Node node) {
    Node preNode = null;
    Node currentNode = head;
    while (currentNode != null && currentNode!= node) {
      preNode = currentNode;
      currentNode = currentNode.nextNode;
    }
    if (currentNode != null) {
      if (preNode != null) preNode.nextNode = currentNode.nextNode;
      else head = currentNode.nextNode;
      currentNode.nextNode = null;
      --size;
      return currentNode;
    }
    else return null;
  }
  
  /**
   * Finds a node by its position in the linked list. If the position
   * does not exist in the linked list, then null will be returned.
   *
   * @param position the position to look for.
   * @return the node that in given position.
   */
  public Node findNode(int position) {
    Node currentNode = head;
    if (position < size) {
      for (int i = 0; i < position; ++i) currentNode = currentNode.nextNode;
      return currentNode;
    }
    else return null;
  }
  
  /**
   * Resets the head and size to null and 0,
   * and thus removes all nodes in the linked list.
   */
  public void resetAll() {
    head = null;
    size = 0;
  }
}
