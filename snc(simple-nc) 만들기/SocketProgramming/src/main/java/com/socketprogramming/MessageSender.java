package com.socketprogramming;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MessageSender extends Thread {

  Socket socket;

  String sendMessage() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("보낼 문자를 입력하세요: ");
    return scanner.nextLine();
  }

  public void setSocket(Socket socket){
    this.socket = socket;
  }

  public void run() {
    PrintWriter printWriter;

    try {
      OutputStream outputStream = socket.getOutputStream();
      printWriter = new PrintWriter(outputStream, true);
    }catch(IOException e){
      throw new RuntimeException(e);
    }

    while (!isInterrupted()) {
      String message = sendMessage();
      printWriter.println(message);
    }
  }
}
