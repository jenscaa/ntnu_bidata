import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class EnkelKlient {
  
  public static void main(String[] args) {
    final int PORTNR = 1250;
    
    Scanner scanner = new Scanner(System.in);
    System.out.print("Oppgi navnet på maskinen der tjenerprogrammet kjører: ");
    String tjenermaskin = scanner.nextLine();
    
    try (Socket forbindelse = new Socket(tjenermaskin, PORTNR);
         InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
         BufferedReader leseren = new BufferedReader(leseforbindelse);
         PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true)) {
      System.out.println("Forbindelse opprettet");
      System.out.println(leseren.readLine());
      
      String line = scanner.nextLine();
      while (!line.equals("")) {
        skriveren.println(line);
        String respons = leseren.readLine();
        System.out.printf("Respons fra tjener: %s\n", respons);
        line = scanner.nextLine();
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}