/**
 * Represents the solution for double hashing in IDATT2101 oving 5 deloppgave 2: Hashtabeller og ytelse.
 * Inherits properties from OpenAddressing.
 *
 * @author Jens Christian Aanestad
 * @version 27.09.2023
 * @see OpenAddressing
 */
class DoubleHash extends OpenAddressing {
  
  /**
   * Initializes the array with a given size.
   *
   * @param size the initialized size of the array.
   */
  public DoubleHash(int size) {
    super(size);
  }
  
  /**
   * Represents the first hash function for this double hash solution.
   * This method takes a prime number 2003 and multiplies with k
   * and at last takes modulo of the array size. This prime number is chosen
   * in order to get a good spread and reduce collisions.
   *
   * @param k a long value.
   * @return a hashed value.
   */
  public int hashFunction1(long k) {
    final int PRIME_CONSTANT = 2003;
    return (int) ((k * PRIME_CONSTANT) % getSize());
  }
  
  /**
   * Represents the second hash function for this double hash solution.
   * To make sure that the probe sequence can enter all positions, we must
   * check if the return value is relatively prime with the array size. This is
   * done with a for-loop. The tradeoff is unfortunately increasing time complexity.
   *
   * @param k a long value.
   * @return a hashed value.
   */
  public int hashFunction2(long k) {
    final int PRIME_CONSTANT = 997;
    int hash = (int) (PRIME_CONSTANT - (k % PRIME_CONSTANT));
    while (gcd(hash, getSize()) != 1) {
      hash++;
    }
    return hash;
  }
  
  /**
   * Puts in a given long value in the array and probes if it gets a collision.
   *
   * @param k the value to put in the array.
   */
  @Override
  public void putIn(long k) {
    int j = probe(k);
    array[j] = k;
  }
  
  /**
   * The probe method for double hashing.
   *
   * @param k a long value.
   * @return a position/index in the array which is not occupied.
   */
  public int probe(long k) {
    int pos = hashFunction1(k);
    if (array[pos] == null) {
      return pos;
    }
    int h2 = hashFunction2(k);
    for (;;) {
      pos = (pos + h2) % getSize();
      if (array[pos] == null) {
        return pos;
      }
      collisions++;
    }
  }
}
