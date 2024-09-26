/**
 * Represents a queue for storing objects. Is used for <i>IDATT2101 oving 6</i>.
 * Much is taken from the book <i>Algoritmer og datastrukturer</i> on page 99.
 *
 * @author Jens Christian Aanestad
 * @version 03.10.2023
 */
public class Queue {
  private final Object[] array;
  private int start = 0;
  private int end = 0;
  private int amount = 0;
  
  /**
   * Initializes a queue with a given size.
   *
   * @param size the size of the queue.
   */
  public Queue(int size) {
    array = new Object[size];
  }
  
  /**
   * Returns true if the amount attribute is 0.
   *
   * @return true if the amount attribute is 0. Returns false if otherwise.
   */
  public boolean isEmpty() {
    return amount == 0;
  }
  
  /**
   * Returns true if all slots in the array is occupied.
   *
   * @return true if all slots in the array is occupied. Returns false if otherwise.
   */
  public boolean isFull() {
    return amount == array.length;
  }
  
  /**
   * Puts an object in the queue. If the queue is full, the object will not be placed.
   *
   * @param e the object to put in the queue.
   */
  public void put(Object e) {
    if (isFull()) return;
    array[end] = e;
    end = (end + 1)% array.length;
    ++amount;
  }
  
  /**
   * Returns the object first in the queue, and removes it from the queue.
   *
   * @return the object first in the queue.
   */
  public Object next() {
    if (!isEmpty()) {
      Object e = array[start];
      start = (start + 1)% array.length;
      --amount;
      return  e;
    }
    else return null;
  }
  
  /**
   * Returns the object first in the queue without removing it from the queue.
   *
   * @return the object first in the queue.
   */
  public Object checkNext() {
    if (!isEmpty()) return array[start];
    else return null;
  }
}