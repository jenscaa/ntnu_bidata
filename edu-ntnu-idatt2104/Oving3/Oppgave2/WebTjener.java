import java.io.*;
import java.net.*;

public class WebTjener {
  
  public static void main(String[] args) {
    final int PORT = 80;
    
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Web server listening on port " + PORT);
      
      while (true) {
        Socket clientSocket = serverSocket.accept();
        handleClientRequest(clientSocket);
      }
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private static void handleClientRequest(Socket clientSocket) {
    try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
    ) {

      StringBuilder headers = new StringBuilder();
      String line;
      
      while ((line = reader.readLine()) != null && !line.isEmpty()) {
        headers.append("<LI>").append(line).append("</LI>\n");
      }

      writer.println("HTTP/1.0 200 OK");
      writer.println("Content-Type: text/html; charset=utf-8");
      writer.println();
      writer.println("<HTML><BODY>");
      writer.println("<H1>Velkommen! Du har koblet deg opp til min enkle web-tjener</H1>");
      writer.println("Header fra klient er:");
      writer.println("<UL>");
      writer.println(headers);
      writer.println("</UL>");
      writer.println("</BODY></HTML>");

      clientSocket.close();
      
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
