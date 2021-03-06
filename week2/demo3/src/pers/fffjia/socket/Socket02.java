package pers.fffjia.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket02 {

  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8802);
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        new Thread(() -> {
          service(socket);
        }).start();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static void service(Socket socket) {
    try {
      PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
      printWriter.println("HTTP/1.1 200 0K");
      printWriter.println("Content-Type: text/html");
      String body = "hello socket";
      printWriter.println("Content-Length: " + body.getBytes().length);
      printWriter.println();
      printWriter.println(body);
      printWriter.close();
      socket.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
