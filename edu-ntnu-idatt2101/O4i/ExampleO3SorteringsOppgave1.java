// Note: This is not source code for Øving 4. This is a test material for deloppgave 2.

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Represent the source code for Øving 3 IDATT2101
 * - Sorting task 1, quicksort with help algorithm.
 * @author Jens Christian Aanestad
 * @version 07.09.2023
 */
class ExampleO3SorteringsOppgave1 {
  
  /**
   * Switching the values on an integer array for two given indexes.
   *
   * @param t the integer array.
   * @param i1 the first index.
   * @param i2 the second index.
   */
  public static void bytt(int[] t, int i1, int i2) {
    if (i1 == i2) {
      return;
    }
    // XOR-operator '^' to change integers without a temporary variable.
    t[i1] = t[i1] ^ t[i2];
    t[i2] = t[i1] ^ t[i2];
    t[i1] = t[i1] ^ t[i2];
  }
  
  /**
   * Represents a sorting method for an integer array with the insert algorithm.
   *
   * @param t the integer array.
   */
  public static void innsettingssort(int []t) {
    for (int j = 1; j < t.length; ++j) {
      int bytt = t[j];
      int i = j - 1;
      while (i >= 0 && t[i] > bytt) {
        t[i + 1] = t[i];
        --i;
      }
      t[i + 1] = bytt;
    }
  }
  
  /**
   * Represents an altered sorting method for an integer array with the insert algorithm.
   * In this method the parameters takes a starting index and an ending index.
   *
   * @param t the integer array.
   * @param v the starting index.
   * @param h the ending index.
   */
  public static void innsettingssort(int[] t, int v, int h) {
    for (int j = v + 1; j <= h; ++j) {
      int bytt = t[j];
      int i = j - 1;
      while (i >= v && t[i] > bytt) {
        t[i + 1] = t[i];
        --i;
      }
      t[i + 1] = bytt;
    }
  }
  
  /**
   * Represents a method for choosing a pivot element.
   *
   * @param t the integer array.
   * @param v the starting index.
   * @param h the ending index.
   * @return the pivot element as an integer m.
   */
  private static int median3sort(int []t, int v, int h) {
    int m = (v + h) / 2;
    if (t[v] > t[m]) bytt(t, v, m);
    if (t[m] > t[h]) {
      bytt(t, m, h);
      if (t[v] > t[m]) bytt(t, v, m);
    }
    return m;
  }
  
  /**
   * Represents the altered quicksort algorithm for sorting an integer array.
   * In this method recursion breaks when an array gets under a "fitting" size.
   *
   * @param t the integer array.
   * @param v the first index.
   * @param h the second index.
   * @param fittingSize an integer representing when the recursion must break.
   */
  public static void quicksort(int []t, int v, int h, int fittingSize) {
    if (h - v > 2) {
      if (h - v < fittingSize) {
        innsettingssort(t, v, h);
      } else {
        int delepos = splitt(t, v, h);
        quicksort(t, v, delepos - 1, fittingSize);
        quicksort(t, delepos + 1, h, fittingSize);
      }
    } else median3sort(t, v, h);
  }
  
  /**
   * Represents the default quicksort algorithm for sorting an integer array.
   *
   * @param t the integer array.
   * @param v the first index.
   * @param h the second index.
   */
  public static void quicksort(int []t, int v, int h) {
    if (h - v > 2) {
      int delepos = splitt(t, v, h);
      quicksort(t, v, delepos - 1);
      quicksort(t, delepos + 1, h);
    } else median3sort(t, v, h);
  }
  
  /**
   * Method to divide the array by placing elements that are smaller than
   * the pivot to the left, and bigger to the right. Returns the new
   * index for the pivot.
   *
   * @param t the integer array.
   * @param v the first index.
   * @param h the second index.
   * @return a new value for the pivot.
   */
  private static int splitt(int []t, int v, int h) {
    int iv, ih;
    int m = median3sort(t, v, h);
    int dv = t[m];
    bytt(t, m, h - 1);
    for (iv = v, ih = h - 1;;) {
      while (t[++iv] < dv) ;
      while (t[--ih] > dv) ;
      if (iv >= ih) break;
      bytt(t, iv, ih);
    }
    bytt(t, iv, h-1);
    return iv;
  }
  
  /**
   * Generates random integer values to an array from 0 to a given boundary.
   *
   * @param t the integer array.
   * @param bound the boundary.
   * @return the given array with random values.
   */
  public static int[] generateRandomValues(int []t, int bound) {
    Random random = new Random();
    for (int i = 0; i < t.length - 1; i++) {
      if (i % 2 == 0) {
        t[i] = 42;
      } else {
        t[i] = random.nextInt(bound);
      }
    }
    return t;
  }
  
  /**
   * Returns true if the array is sorted.
   *
   * @param t the integer array.
   * @return boolean.
   */
  public static boolean testSort(int []t) {
    for (int i = 1; i < t.length; i++) {
      if (t[i] < t[i-1]) return false;
    }
    return true;
  }
  
  /**
   * Sums all the values int the array.
   *
   * @param t the integer array.
   * @return the sum of all values in the array.
   */
  public static int sum(int []t) {
    int sum = 0;
    for (int i : t) {
      sum += i;
      
    }
    return sum;
  }
  
  /**
   * Represents the timer for the quicksort algorithm combined with the help algorithm.
   *
   * @param t the integer array.
   * @param fittingSizes the list with integers to trigger recursion break.
   */
  public static void sortingTimerWithList(int []t, List<Integer> fittingSizes) {
    for (int fittingSize : fittingSizes) {
      System.out.println("_".repeat(65));
      Date start = new Date();
      int rounds = 0;
      double tid;
      Date end;
      do {
        quicksort(t.clone(), 0, t.clone().length - 1, fittingSize);
        end = new Date();
        ++rounds;
      } while (end.getTime()-start.getTime() < 1000);
      tid = (double) (end.getTime()-start.getTime()) / rounds;
      System.out.println("Millisekund pr. runde: " + tid + ", Terksel: " + fittingSize + "\n");
    }
  }
  
  /**
   * Represents the timer for the quicksort algorithm.
   *
   * @param t the integer array.
   */
  public static void sortingTimer(int []t) {
    Date start = new Date();
    int rounds = 0;
    double tid;
    Date end;
    do {
      quicksort(t.clone(), 0, t.clone().length - 1);
      end = new Date();
      ++rounds;
    } while (end.getTime()-start.getTime() < 1000);
    tid = (double) (end.getTime()-start.getTime()) / rounds;
    System.out.println("Millisekund pr. runde: " + tid + "\n");
    
  }
  
  
  
  /**
   * Main method - the entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    int[] tabell = new int[1000000];
    generateRandomValues(tabell, 100000);
    
    System.out.println("Tidtakning for quicksort uten hjelpealgoritmer:");
    sortingTimer(tabell.clone());
    
    
    System.out.println("*".repeat(65) + "\n\nTidtakning for quicksort med hjelpealgoritme:\n");
    // Leter etter en passende størrelse hvor som gir raskest resultat.
    sortingTimerWithList(tabell, List.of(0, 1, 10, 30, 50, 70, 100, 300, 500, 700, 1000, 2000, 3000, 5000, 7000, 10000, 20000));
    
    System.out.println("*".repeat(65) + "\n\nTidtakning for quicksort med hjelpealgoritme for allerede sorterte tabller:\n");
    // Sorter en allerede sortert tabell.
    quicksort(tabell, 0, 1);
    // Nå sorterer algoritmen den sorterte tabellen "tabell".
    sortingTimerWithList(tabell, List.of(0, 1, 10, 30, 50, 70, 100, 300, 500, 700, 1000, 2000, 3000, 5000, 7000, 10000, 20000));
    
    
    
    // Kommer til et område hvor disse tallene er interessante.
    generateRandomValues(tabell, 100000); // For å ikke sortere allerede sorterte tabeller.
    System.out.println("*".repeat(65) + "\n\nTidtakning for quicksort med hjelpealgoritme for lovende verdier:\n");
    sortingTimerWithList(tabell, List.of(10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80));
  }
}
