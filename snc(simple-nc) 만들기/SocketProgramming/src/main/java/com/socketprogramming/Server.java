package com.socketprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;

public class Server implements Runnable{

  int port;

  public Server(int port){
    this.port = port;
  }
  public void run(){

    try(ServerSocket serverSocket = new ServerSocket(port)){
      Socket socket = connect(serverSocket);

      MessageSender messageSender = new MessageSender();
      messageSender.setSocket(socket);
      messageSender.start();

      while(!Thread.currentThread().isInterrupted()) {
        String message = getMessage(socket);
        System.out.println("클라이언트로부터 " + message + "를 받았습니다.");
      }

    } catch (IOException e) {
      System.exit(0);
//      throw new RuntimeException(e);
    }
  }


  public Socket connect(ServerSocket serverSocket){
    try{
      System.out.println("클라이언트가 서버에 연결을 시도합니다.");
      Thread.sleep(3000);
      Socket socket = serverSocket.accept();
      return socket;
    }
    catch(InterruptedException | IOException e){
      throw new RuntimeException(e);
    }
  }

  public String getMessage(Socket socket) throws IOException{
      InputStream inputStream = socket.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
      String inputMessage = bufferedReader.readLine();

      return inputMessage;
    }


}
