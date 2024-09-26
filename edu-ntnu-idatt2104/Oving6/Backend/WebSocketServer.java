import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebSocketServer {
  static ArrayList<OutputStream> clientOutputs = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    final int PORT = 1250;

    ServerSocket server = new ServerSocket(PORT);
    System.out.println("WebSocket server is running on port " + PORT);

    while (true) {
      Socket client = server.accept();
      System.out.println("Client connected\n\n");

      Thread clientThread = new Thread(() -> {
        try {
          handleClient(client);
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
      clientThread.start();
    }
  }

  public static void handleClient(Socket client) throws IOException {
    try {
      InputStream in = client.getInputStream();
      OutputStream out = client.getOutputStream();
      clientOutputs.add(out);
      Scanner scanner = new Scanner(in, "UTF-8");

      try {
        String data = scanner.useDelimiter("\\r\\n\\r\\n").next();
        Matcher get = Pattern.compile("^GET").matcher(data);

        System.out.println(data + "\n\n");

        if (get.find()) {
          Matcher match = Pattern.compile("Sec-WebSocket-Key: (.*)").matcher(data);
          match.find();
          byte[] response = ("HTTP/1.1 101 Switching Protocols\r\n"
                  + "Connection: Upgrade\r\n"
                  + "Upgrade: websocket\r\n"
                  + "Sec-WebSocket-Accept: "
                  + Base64.getEncoder().encodeToString(MessageDigest.getInstance("SHA-1").digest((match.group(1) + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11").getBytes("UTF-8")))
                  + "\r\n\r\n").getBytes("UTF-8");
          out.write(response, 0, response.length);
          System.out.println(new String(response, StandardCharsets.UTF_8));
        }

        while (true) {
          Frame frame = Frame.parseFrame(in);
          Frame.mask_payload(frame);
          System.out.println("Received message: " + new String(frame.payload, "UTF-8"));
          for (OutputStream clientOutput : clientOutputs) {
            frame.transmit(clientOutput);
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      client.close();
    }
  }
}
