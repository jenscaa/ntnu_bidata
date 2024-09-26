import java.io.*;
import java.net.*;
import java.util.Objects;

class EnkelTjener {
  
  public static String simpleArithmetic(String expression) {
    String[] terms = expression.split(" +");
    
    if (terms.length != 3 || !(Objects.equals(terms[1], "+") || Objects.equals(terms[1], "-"))) {
      return "Feil format!";
    }
    if (terms[1].equals("+")) {
      return String.valueOf(Integer.parseInt(terms[0]) + Integer.parseInt(terms[2]));
    }
    return String.valueOf(Integer.parseInt(terms[0]) - Integer.parseInt(terms[2]));
  }
  
  public static void main(String[] args) {
    final int PORTNR = 1250;
    
    System.out.println("Venter på forbindelse...");
    // Try-with-resource in order to provide closing.
    try (ServerSocket tjener = new ServerSocket(PORTNR);
         Socket forbindelse = tjener.accept();
         InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
         BufferedReader leseren = new BufferedReader(leseforbindelse);
         PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true)) {
      System.out.println("Forbindelse opprettet");
      skriveren.println("Gi et regnestykke på formen: x + y");
      
      String expression = leseren.readLine();
      while (expression != null) {
        System.out.println("En klient spurte om: " + expression + ". Svaret er:  " + simpleArithmetic(expression));
        skriveren.println(expression + " = " + simpleArithmetic(expression));
        expression = leseren.readLine();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}