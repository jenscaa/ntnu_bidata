import java.io.*;
import java.net.*;
import java.util.Objects;

public class Tjener {

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
    final int PORTNR = 4445;
    
    System.out.println("Venter på forbindelse...");
    try (ServerSocket tjener = new ServerSocket(PORTNR)) {
      while (true) {
        Socket forbindelse = tjener.accept();
        System.out.println("Ny klientforbindelse mottatt");
  
        KlientThread klientThread = new KlientThread(forbindelse);
        klientThread.start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class KlientThread extends Thread {
  private Socket clientSocket;
  
  public KlientThread(Socket clientSocket) {
    this.clientSocket = clientSocket;
  }
  
  @Override
  public void run() {
    try (InputStreamReader leseforbindelse = new InputStreamReader(clientSocket.getInputStream());
         BufferedReader leseren = new BufferedReader(leseforbindelse);
         PrintWriter skriveren = new PrintWriter(clientSocket.getOutputStream(), true)) {
      
      System.out.println("Forbindelse opprettet med klient " + this.getId());
      skriveren.println("Oppgi et regnestykke på formen: x + y");
      
      String expression = leseren.readLine();
      while (expression != null) {
        System.out.println("Klient " + this.getId() + " spurte om: " + expression + ". Svaret er: " + Tjener.simpleArithmetic(expression));
        skriveren.println(expression + " = " + Tjener.simpleArithmetic(expression));
        expression = leseren.readLine();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        clientSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
