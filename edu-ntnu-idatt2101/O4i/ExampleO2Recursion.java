// Note: This is not source code for Øving 4. This is a test material for deloppgave 2.

import java.util.*;

/**
 * Represent the source code for Øving 2 IDATT2101 - Recursion.
 * @author Jens Christian Aanestad
 * @version 30.08.2023
 */
public class ExampleO2Recursion {
  
  /**
   * Represents the first alternative method
   * for multiplication with an integer and a double.
   *
   * @param n an integer for one factor.
   * @param x a double for the other factor.
   * @return The multiplication of n and x.
   */
  public static double method1(int n, double x) {
    if (n == 1) {
      return x;
    } else {
      return method1(n - 1,  x) + x;
    }
  }
  
  /**
   * Rounds a double to two decimals.
   *
   * @param product the double to round.
   * @return the rounded double value.
   */
  public static double roundTwoDecimals(double product) {
    return (double) ((int) Math.round(product * 100)) / 100;
  }
  
  /**
   * Represents the second alternative method
   * for multiplication with an integer and a double.
   *
   * @param n an integer for one factor.
   * @param x a double for the other factor.
   * @return The multiplication of n and x.
   */
  public static double method2(int n, double x) {
    if (n == 1) {
      return x;
    }
    if (n % 2 == 0) {
      return method2(n/2, x + x);
    } else {
      return method2((n - 1)/2, x + x) + x;
    }
  }
  
  /**
   * Generates a list with a random integer between 0 and 10,
   * and a random double between 0 and 3.
   *
   * @return an ArrayList with the random integer and double values.
   */
  public static ArrayList<Object> generateRandomNumbers() {
    Random random = new Random();
    int n = random.nextInt(11);
    double x = random.nextDouble(4);
    return new ArrayList<Object>(List.of(n, x));
  }
  
  /**
   * Represent the timer for both methods.
   */
  public static void methodTimer(List<Integer> listOfIntegers) {
    listOfIntegers.forEach(n -> {
      System.out.println("_".repeat(65));
      for (int i = 1; i <= 2; i++) {
        Date start = new Date();
        int rounds = 0;
        double tid;
        Date end;
        do {
          if (i == 1) {
            method1(n, 13.7);
          } else {
            method2(n, 13.7);
          }
          end = new Date();
          ++rounds;
        } while (end.getTime()-start.getTime() < 1000);
        tid = (double) (end.getTime()-start.getTime()) / rounds;
        System.out.println("n = " + n + ", Metode " + i + ", Millisekund pr. runde: " + tid + "\n");
      }
    });
  }
  
  /**
   * Main method - Entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    methodTimer(List.of(2, 5, 10, 50, 100, 250, 500, 1000, 3000, 5000));
  }
}