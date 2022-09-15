package socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{

    @Override
    public void run(){
        try(Socket socket = new Socket("localhost", 1522)){
            Scanner scanner = new Scanner(System.in);
            System.out.print("클라이언트가 보낼 메시지를 입력하세요: ");
            String message = scanner.nextLine();
            sendMessage(socket, message);
            getMessage(socket);
        }catch(IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessage(Socket socket, String message){
        try{
            OutputStream outputStream = socket.getOutputStream(); //데이터를 내보내는 스트림
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            System.out.println("클라이언트가 " + message + "를 보냅니다.");
            printWriter.println(message);
        }catch(IOException e){
            throw new RuntimeException("전송오류" + e);
        }
    }

    public void getMessage(Socket socket) throws InterruptedException {
        try{
            System.out.println("클라이언트가 메시지를 받을 준비중입니다..");
            InputStream inputStream = socket.getInputStream();
            DataInputStream dis = new DataInputStream(inputStream);

            System.out.println("클라이언트가 " + dis.readUTF() + " 메시지를 받았습니다.");
        }catch(IOException e){
            throw new RuntimeException("전송오류" + e);
        }
    }
}
