package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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

  public static void main(String[] args) throws IOException {
    System.out.println("Tjener startet...");
    new TjenerThread().start();
  }
}

class TjenerThread extends Thread {

  public TjenerThread() throws IOException {
    this("TjenerThread");
  }

  public TjenerThread(String name) throws IOException {
    super(name);
  }

  @Override
  public void run() {

    try(DatagramSocket socket = new DatagramSocket(4445)) {
      while (true) {

        byte[] buf = new byte[256];

        // receive request
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // figure out response
        String recievedData = new String(packet.getData(), 0, packet.getLength());
        String response = Tjener.simpleArithmetic(recievedData);
        System.out.println("Fikk følgende uttrykk: " + recievedData);
        System.out.println("Utregnet svar: " + response);
        buf = response.getBytes();

        // Check if received data is empty
        if (recievedData.isEmpty()) {
          return;
        } else {
          // send the response to the client at "address" and "port"
          InetAddress address = packet.getAddress();
          int port = packet.getPort();
          packet = new DatagramPacket(buf, buf.length, address, port);
          socket.send(packet);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
