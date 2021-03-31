package pers.fffjia.socket;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Socket03 {

  public static void main(String[] args) throws IOException {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
    ServerSocket serverSocket = new ServerSocket(8803);
    while (true) {
      try {
        Socket socket = serverSocket.accept();
        executorService.submit(() -> service(socket));
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
