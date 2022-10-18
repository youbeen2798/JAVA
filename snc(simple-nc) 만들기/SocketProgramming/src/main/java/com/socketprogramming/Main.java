package com.socketprogramming;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    String inputMessage = scanner.nextLine();

    String[] strArr = inputMessage.split(" ");
    if (strArr[1].equals("-l")) {
      System.out.println("서버");
      int port = Integer.parseInt(strArr[2]);
      Thread serverThread = new Thread(new Server(port));
      serverThread.run();
    } else {
      String host = strArr[1];
      int port = Integer.parseInt(strArr[2]);
      Thread clientThread = new Thread(new Client(host, port));
      clientThread.run();
    }
  }

}
