package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Klient {

  public static void main(String[] args) {

    if (args.length != 1) {
      System.out.println("Usage: java QuoteClient <hostname>");
      return;
    }
    final String hostName = args[0];

    Scanner scanner = new Scanner(System.in);
    byte[] buf = new byte[256];

    try (DatagramSocket socket = new DatagramSocket()) {

      System.out.println("Oppgi et regnestykke på formen: x + y");
      String line = scanner.nextLine();
      while (!line.equals("")) {

        // send request
        buf = line.getBytes();

        // Print the content of the buffer before sending
        System.out.println("Sender: " + new String(buf));

        InetAddress address = InetAddress.getByName(hostName);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
        socket.send(packet);

        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Svar: " + received);

        line = scanner.nextLine();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
