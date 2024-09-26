import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Represents the source code for Øving 4 IDATT2101 deloppgave 2: Matching parentheses,
 * square brackets, and curly brackets.
 *
 * @author Jens Christian Aanestad
 * @version 14.09.2023
 */
public class MatchingParenthesesAndBrackets {
  
  /**
   * Reads a file line by line, and converts it into a string.
   *
   * @param file the File to read.
   * @return the file's content converted to a string.
   * @throws IOException if fails to find the file.
   * @see FileReader
   * @see BufferedReader
   */
  public static String FileReader(File file) throws IOException {
    StringBuilder fileData = new StringBuilder();
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        fileData.append(line).append("\n");
      }
    } catch (IOException e) {
      throw new IOException("An error occurred when reading the file");
    }
    return fileData.toString();
  }
  
  /**
   * Checks if given input with parentheses, square brackets, and curly brackets are correctly
   * placed.
   *
   * @param input the input to check.
   * @return true if correctly placed. False otherwise.
   */
  public static boolean isParenthesesAndBracketsMatched(String input) {
    // Using the stack data structure to solve placement.
    Stack<Character> stack = new Stack<Character>();
    
    for (char character : input.toCharArray()) {
      if (character == '(' || character == '[' || character == '{') {
        stack.push(character);
      } else if (character == ')' || character == ']' || character == '}') {
        // If the stack is empty when meeting closing parentheses and brackets, then it must fail.
        if (stack.isEmpty()) {
          return false;
        }
        Object lastIn = stack.pop();
        // Checks if the closing parentheses/brackets matches with the starting parentheses/brackets last in the stack.
        if ((character == ')' && (char) lastIn != '(') ||
            (character == ']' && (char) lastIn != '[') ||
            (character == '}' && (char) lastIn != '{')) {
          return false;
        }
      }
    }
    // Finally if the stack is empty, then all parentheses and brackets matches.
    return stack.isEmpty();
  }
  
  /**
   * Converts a boolean to a sentence.
   * @param b matched parenthesis boolean.
   * @return the sentence.
   */
  public static String booleanToSentence(boolean b) {
    if (b) {
      return "Parenthesis and brackets are matched";
    } else {
      return "Parenthesis and brackets are not matched";
    }
  }
  
  /**
   * Main method - entry point.
   *
   * @param args String[]
   */
  public static void main(String[] args) {
    try {
      System.out.println("Parenthesis matching:\n");
      
      System.out.println("JosephusProblem.java:        " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("JosephusProblem.java")))));
      
      System.out.println("Soldier.java:                " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("Soldier.java")))));
      
      System.out.println("ExampleO1StockChange.java:   " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("ExampleO1StockChange.java")))));
      
      System.out.println("ExampleO2Recursion.java:     " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("ExampleO2Recursion.java")))));
      
      System.out.println("ExampleO3SorteringsOppgave1: " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("ExampleO3SorteringsOppgave1.java")))));
      
      System.out.println("ErrorExampleInput:           " +
              booleanToSentence(isParenthesesAndBracketsMatched(
              FileReader(new File("ErrorExampleInput.java")))));
      
      System.out.println("(){{}[[]][]}{()}:            " +
              booleanToSentence(isParenthesesAndBracketsMatched("(){{}[[]][]}{()}")));
      
      System.out.println("{([()])}]:                   " +
              booleanToSentence(isParenthesesAndBracketsMatched("{([()])}]")));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
