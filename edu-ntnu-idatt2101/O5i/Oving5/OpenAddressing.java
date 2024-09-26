import java.util.List;
import java.util.Random;

/**
 * Represents the solution for IDATT2101 oving 5 deloppgave 2: Hashtabeller og ytelse.
 * This class super to the subclasses DoubleHash and LinearHash which also represents a part solution.
 *
 * @author Jens Christian Aanestad
 * @version 27.09.2023
 * @see DoubleHash
 * @see LinearProbe
 */
public abstract class OpenAddressing {
  protected final Long[] array;
  protected long collisions = 0;
  
  /**
   * Initializes the array with a given size. Long[] is used instead of int[].
   * The reason for this is simply because it allows us check for null at indexes.
   *
   * @param size the initialized size of the array.
   */
  public OpenAddressing(int size) {
    this.array = new Long[size];
  }
  
  /**
   * Returns the size of the array.
   *
   * @return the size of the array.
   */
  public int getSize() {
    return array.length;
  }
  
  /**
   * Returns the amount of collisions occurred.
   *
   * @return number of collisions.
   */
  public long getCollisions() {
    return collisions;
  }
  
  /**
   * Resets the entire array by setting every element to null, and collisions to 0.
   */
  public void reset() {
    for (int i = 0; i < getSize(); i++) {
      array[i] = null;
    }
    collisions = 0;
  }
  
  /**
   * Puts in the values of one array in this array.
   * Resets this array before insertion.
   *
   * @param ht Long array.
   */
  public void putInArray(Long[] ht) {
    this.reset();
    for (long i : ht) {
      putIn(i);
    }
  }
  
  /**
   * Empty putIn method which is intended to be overwritten.
   *
   * @param i value to put in the array.
   */
  public abstract void putIn(long i);
  
  /**
   * Generates a Long array of unique and random integer values.
   * This method presumes that m is greater than 0.
   *
   * @param m the initialized size value of the random array.
   * @return an array with unique and random values.
   */
  public static Long[] generateRandomArray(int m) {
    Random random = new Random();
    Long[] array = new Long[m];
    array[0] = random.nextLong(1000) + 1;
    for (int i = 1; i < m; i++) {
      array[i] = array[i-1] + random.nextLong(1000) + 1;
    }
    shuffleArray(array);
    return array;
  }
  
  /**
   * Shuffles an array. This method is much taken from stackoverflow.com
   * (The Fischer-Yates (Knuth) Shuffle Algorithm).
   *
   * @param array the array to be shuffled.
   */
  public static void shuffleArray(Long[] array) {
    Random random = new Random();
    int arrayLength = array.length;
    
    for (int i = arrayLength - 1; i > 0; i--) {
      // Generate a random index between 0 and i (inclusive)
      int randomIndex = random.nextInt(i + 1);
      
      // Swap array[i] and array[randomIndex]
      Long temp = array[i];
      array[i] = array[randomIndex];
      array[randomIndex] = temp;
    }
  }
  
  /**
   * Divides an array by multiplying the array length with a given portion.
   * This portion (double) is presumed to be between 0 and 1.
   *
   * @param array the array to divide.
   * @param portion the portion to remain of the array size.
   * @return a divided array.
   */
  public static Long[] divideArray(Long[] array, double portion) {
    Long[] newArray = new Long[(int) (array.length * portion)];
    for (int i = 0; i < (int) (array.length * portion); i++) {
      newArray[i] = array[i];
    }
    return newArray;
  }
  
  /**
   * Returns the GCD (the Greatest Common Divisor) between two numbers.
   * If GCD is 1, the numbers are relatively prime.
   *
   * @param a a number
   * @param b a number
   * @return the gcd between a and b.
   */
  protected static int gcd(int a, int b) {
    while (b != 0) {
      int temp = b;
      b = a % b;
      a = temp;
    }
    return a;
  }
  
  /**
   * The timer for linear probing and double hashing. Prints out the measured time for
   * each individual insertions. Each open address solution will be measured for
   * 50%, 80%, 90%, 99%, and 100% of fill rates. This method also prints
   * out the amount of collisions for each insertion of the fill rates.
   *
   * @param size the initialized size of the arrays.
   */
  public static void timer(int size) {
  
    LinearProbe lh = new LinearProbe(size);
    DoubleHash dh = new DoubleHash(size);
  
    Long[] randomArray = generateRandomArray(size);
  
    for (OpenAddressing oa : List.of(dh, lh)) {
      // De ulike fyllingsgradene som skal tidsmåles.
      for (double i : List.of(.50, .80, .90, .99, (double) 1)) {
        Long[] dividedRandomArray = divideArray(randomArray, i);
        
        // Tidsmålingene.
        long startTime = System.nanoTime();
        oa.putInArray(dividedRandomArray);
        long endTime = System.nanoTime();
  
        // Print resultat.
        double timeResult = (double) (endTime - startTime) / 1000000;
        System.out.printf("\n%s | Fyllingsgrad: %s%%%s | Kollisjoner: %d%s | Tid (millisekunder): %f"
                , oa instanceof LinearProbe ? "Lineær probing" : "Dobbel hashing", (int) (i * 100)
                , " ".repeat(3 - String.valueOf((int) (i * 100)).length()), oa.getCollisions()
                , " ".repeat(Math.abs(11 - String.valueOf(oa.getCollisions()).length()))
                , timeResult);
      }
    }
  }
  
  /**
   * Main method - Entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    // Valgt størresle på tabellen. Funker også for 2^24 = 16777216 og primtallet 10000019.
    final int m = 10000000;
    timer(m);
    System.out.println("\n");
  }
}

