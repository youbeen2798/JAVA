package com.socketprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {

  int port;
  String host;

  public Client(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void run() {

    try {
      Socket socket = new Socket(host, port);

      MessageSender messageSender = new MessageSender();
      messageSender.setSocket(socket);
      messageSender.start();

      while (!Thread.currentThread().isInterrupted()) {
        String message = getMessage(socket);
        System.out.println("클라이언트로부터 " + message + "를 받았습니다.");
      }

    } catch (IOException e) {
      System.exit(0);
    }
  }
  
  public String getMessage(Socket socket) throws IOException {
    String inputMessage = null;
    InputStream inputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
    inputMessage = bufferedReader.readLine();
    return inputMessage;

  }

}
