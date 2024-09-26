/**
 * Represents the solution for linear probing in IDATT2101 oving 5 deloppgave 2: Hashtabeller og ytelse.
 * Inherits properties from OpenAddressing.
 *
 * @author Jens Christian Aanestad
 * @version 27.09.2023
 * @see OpenAddressing
 */
class LinearProbe extends OpenAddressing {
  
  /**
   * Initializes the array with a given size.
   *
   * @param size the initialized size of the array.
   */
  public LinearProbe(int size) {
    super(size);
  }
  
  /**
   * Represents the hash function for this linear probe solution.
   * This method takes a prime number 2003 and multiplies with k
   * and at last takes modulo of the array size. This prime number is chosen
   * in order to get a good spread and reduce collisions.
   *
   * @param k a long value.
   * @return a hashed value.
   */
  public int hashFunction(long k) {
    final int PRIME_CONSTANT = 2003;
    return (int) ((k * PRIME_CONSTANT) % getSize());
  }
  
  /**
   * Puts in a given long value in the array and probes if it gets a collision.
   *
   * @param k the value to put in the array.
   */
  @Override
  public void putIn(long k) {
    int m = getSize();
    int h = hashFunction(k);
    for (int i = 0; i < m; ++i) {
      int j = probe(h, i);
      if (array[j] == null) {
        array[j] = k;
        return;
      } else {
        collisions++;
      }
    }
  }
  
  /**
   * The probe method for linear probing.
   *
   * @param h the hashed value.
   * @param i the value to increment with h before modulo of array size.
   * @return a position/index in the array which is not occupied.
   */
  public int probe(int h, int i) {
    return (h + i) % getSize();
  }
}
