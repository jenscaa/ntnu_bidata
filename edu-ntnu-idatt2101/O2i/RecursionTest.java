import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecursionTest {
  ArrayList<Object> randomNumbers;
  int n;
  double x;
  
  @Before
  public void setUp() {
    randomNumbers = Recursion.generateRandomNumbers();
    n = (Integer) randomNumbers.get(0);
    x = (Double) randomNumbers.get(1);
  }
  
  @Nested
  @DisplayName("Tests for method 1")
  public class Method1Test {
    
    @Test
    @DisplayName("Mandatory test for method 1")
    public void MandatoryMethod1Test() {
      // Eksempel verdier fra oppgavearket.
      assertEquals(32.5, Recursion.roundTwoDecimals(Recursion.method1(13, 2.5)));
      assertEquals(141.4, Recursion.roundTwoDecimals(Recursion.method1(14, 10.1)));
      // Helt tilfeldige verdier.
      assertEquals(87.1, Recursion.roundTwoDecimals(Recursion.method1(13, 6.7)));
      assertEquals(2843.4, Recursion.roundTwoDecimals(Recursion.method1(21, 135.4)));
      assertEquals(483.5, Recursion.roundTwoDecimals(Recursion.method1(50, 9.67)));
    }
  
    @Test
    @DisplayName("Random test for method 1")
    public void RandomMethod1Test() {
      randomNumbers = Recursion.generateRandomNumbers();
      n = (Integer) randomNumbers.get(0);
      x = (Double) randomNumbers.get(1);
      assertEquals(Recursion.roundTwoDecimals(n*x), Recursion.roundTwoDecimals(Recursion.method1(n, x)));
    }
  }
  
  @Nested
  @DisplayName("Tests for method 2")
  public class Method2Test {
    
    @Test
    @DisplayName("Mandatory test for method 2")
    public void MandatoryMethod2Test() {
      // Eksempel verdier fra oppgavearket.
      assertEquals(32.5, Recursion.roundTwoDecimals(Recursion.method2(13, 2.5)));
      assertEquals(141.4, Recursion.roundTwoDecimals(Recursion.method2(14, 10.1)));
      // Helt tilfeldige verdier.
      assertEquals(87.1, Recursion.roundTwoDecimals(Recursion.method2(13, 6.7)));
      assertEquals(2843.4, Recursion.roundTwoDecimals(Recursion.method2(21, 135.4)));
      assertEquals(483.5, Recursion.roundTwoDecimals(Recursion.method2(50, 9.67)));
    }
  
    @Test
    @DisplayName("Random test for method 2")
    public void RandomMethod2Test() {
      randomNumbers = Recursion.generateRandomNumbers();
      n = (Integer) randomNumbers.get(0);
      x = (Double) randomNumbers.get(1);
      assertEquals(Recursion.roundTwoDecimals(n*x), Recursion.roundTwoDecimals(Recursion.method2(n, x)));
    }
  }
}
