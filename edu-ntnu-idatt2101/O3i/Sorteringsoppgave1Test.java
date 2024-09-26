import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class Sorteringsoppgave1Test {
  private int []tabell = new int[1000000];
  
  @BeforeEach
  public void setUp() {
    Sorteringsppgave1.generateRandomValues(tabell, 100000);
  }
  @AfterEach
  public void reset() {
    Sorteringsppgave1.generateRandomValues(tabell, 100000);
  }
  
  @Nested
  @DisplayName("Tests for correct sort")
  public class CorrectSortTest {
    
    @Test
    @DisplayName("Correct sort test for Quicksort")
    public void wrongSortForNosort() {
      // Jo mindre det "tilfeldigvis" genereres en tallrekke i stigende rekkefølge.
      assertEquals(false, Sorteringsppgave1.testSort(tabell));
    }
    
    @Test
    @DisplayName("Correct sort test for Quicksort")
    public void correctSortForQuicksort() {
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1);
      assertEquals(true, Sorteringsppgave1.testSort(tabell));
    }
    
    @Test
    @DisplayName("Correct sort test for Quicksort with a help algorithm")
    public void correctSortForQuicksortWithHelpAlgorithm() {
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1, 100);
      assertEquals(true, Sorteringsppgave1.testSort(tabell));
      Sorteringsppgave1.generateRandomValues(tabell, 100000);
  
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1, 500);
      assertEquals(true, Sorteringsppgave1.testSort(tabell));
      Sorteringsppgave1.generateRandomValues(tabell, 100000);
  
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1, 1000);
      assertEquals(true, Sorteringsppgave1.testSort(tabell));
    }
  }
  
  @Nested
  @DisplayName("Tests for correct sum")
  public class CorrectSumTest {
  
    @Test
    @DisplayName("Correct sum for Quicksort")
    public void CorrectSumForQuicksort() {
      // tabellen skal være random før denne testen.
      int sum1 = Sorteringsppgave1.sum(tabell);
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1);
      int sum2 = Sorteringsppgave1.sum(tabell);
      assertEquals(sum1, sum2);
    }
  
    @Test
    @DisplayName("Correct sum for Quicksort with a help algorithm")
    public void CorrectSumForQuicksortWithHelpAlgorithm() {
      // Tabellen skal være tilfeldig før denne testen.
      int sum1 = Sorteringsppgave1.sum(tabell);
      Sorteringsppgave1.quicksort(tabell, 0, tabell.length-1, 1000);
      int sum2 = Sorteringsppgave1.sum(tabell);
      assertEquals(sum1, sum2);
    }
  }
}
