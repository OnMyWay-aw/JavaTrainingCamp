package pers.fffjia.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Socket01 {
  public static void main(String[] args) throws IOException {
    ServerSocket serverSocket = new ServerSocket(8801);
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        service(socket);
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
