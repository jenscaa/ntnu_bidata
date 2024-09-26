import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Represents the solution for Øving 1 IDATT2101 - Stock change.
 *
 * @author Jens Christian Aanestad
 * @version 24.08.2023
 */
public class StockChange {
  List<Integer> listOfStockChanges;
  /**
   * Default constructor. Initializes a new StockChange object.
   */
  public StockChange() {}
  
  /**
   * Initializes a new StockChange object.
   *
   * @param listOfStockChanges a list of integers representing stock changes.
   */
  public StockChange(List<Integer> listOfStockChanges) {
    this.listOfStockChanges = listOfStockChanges;
  }
  
  /**
   * Sets the listOfStockChanges attribute to a new list.
   *
   * @param listOfStockChanges a list of integers representing stock changes.
   */
  public void setListOfStockChanges(List<Integer> listOfStockChanges) {
    this.listOfStockChanges = listOfStockChanges;
  }
  
  /**
   * Finds the best possible profit from a list with stock changes.
   *
   * @return String representation of the investing day, sell day, and best earning.
   */
  public String findBestProfit() {                                // 0 Deklarasjon
    int bestProfit = 0;                                           // 1 Tilordning
    int investingDay = 0;                                         // 1 Tilordning
    int sellDay = 0;                                              // 1 Tilordning
    int currentProfit;                                            // 1 Deklerasjon
    for (int i = 0; i < listOfStockChanges.size(); i++) {         // 1 Tilordning, n sammenlikninger, n inkremeringer
      currentProfit = 0;                                          // n Tilordninger
      for (int j = i + 1; j < listOfStockChanges.size(); j++) {   // n - 1 Tilordninger, n - 1 Addisjoner, 1/2 * (n + 1) * n sammenlikninger, 1/2 * (n + 1) * n inkremeringer
        currentProfit += listOfStockChanges.get(j);               // 1/2 * n * (n + 1) Addisjoner, 1/2 * n * (n + 1) Tabellisteoppslag
        if (currentProfit > bestProfit) {                         // 1/2 * n * (n + 1) sammenlikninger
          bestProfit = currentProfit;                             // ?    (1/2 * n * (n + 1) Tilordninger)
          investingDay = i + 1;                                   // ?    (1/2 * n * (n + 1) Tilordninger, 1/2 * n * (n + 1) Addisjoner)
          sellDay = j + 1;                                        // ?    (1/2 * n * (n + 1) Tilordninger, 1/2 * n * (n + 1) Addisjoner)
        }                                                         // 0
      }                                                           // 0
    }                                                             // 0
    return "InvestDay: " + investingDay + ", SellDay: "           // 1 Return
            + sellDay + ", Profit: " + bestProfit;
  }                                                               // Totalt: 5n**2 + 10n +4 --> O(n**2)
  
  /**
   * Generates a random list with n integers in the range from -15 to 15.
   * This is done in order to simulate likely stock changes.
   *
   * @param n an integer for the number of data/stocks to generate.
   * @return randomNumbers - a list of numbers representing stocks changes.
   */
  public static List<Integer> generateRandomNumbers(int n) {
    List<Integer> randomNumbers = new ArrayList<>();
    Random random = new Random();
    
    for (int i = 0; i < n; i++) {
      int randomNumber = random.nextInt (-15, 16);
      randomNumbers.add(randomNumber);
    }
    return randomNumbers;
  }
  
  /**
   * Represents a timer for the findBestProfit.
   *
   * @param listOfNumbers a list of numbers representing the
   * amount of change values each list should have.
   */
  public static void findBestProfitTimer(List<Integer> listOfNumbers) {
    StockChange stockChange = new StockChange();
    listOfNumbers.forEach(n -> {
      Date start = new Date();
      int rounds = 0;
      double tid;
      Date end;
  
      do {
        stockChange.setListOfStockChanges(generateRandomNumbers(n));
        stockChange.findBestProfit();
        end = new Date();
        ++rounds;
      } while (end.getTime()-start.getTime() < 1000);
      tid = (double)
              (end.getTime()-start.getTime()) / rounds;
      System.out.println(stockChange.findBestProfit());
      System.out.println("Millisekund pr. runde: " + tid + "\n");
    });
  }
  

  /**
   * Main method.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    findBestProfitTimer(List.of(100, 1000, 10000, 100000, 1000000));
  }
}
