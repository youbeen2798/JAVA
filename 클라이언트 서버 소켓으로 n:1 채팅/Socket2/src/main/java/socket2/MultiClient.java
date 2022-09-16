package socket2;

import java.io.*;
import java.net.Socket;

public class MultiClient implements Runnable{

    @Override
    public synchronized void run(){

        MessageReceiver messageReceiver = null;
        MessageSender messageSender = null;

        try(Socket socket = new Socket("localhost", 1522)){
            messageSender = createMessageSender(socket);
            messageReceiver = createMessageReceiver(socket);
            wait(); //wait나 sleep인 상태에서만 interrupted exception이 생긴다.
        }catch(IOException e){
            throw new RuntimeException("연결 오류", e);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }finally{
            //client가 interrupt되면 messageSender,messageReceiver도 interrupt되기 위해서
            messageSender.interrupt();
            messageReceiver.interrupt();
        }
    }

    public void sendMessage(Socket socket,String message){
        try{
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            System.out.println("클라이언트가 서버에게 " + message + "라는 메세지를 보냅니다");
            printWriter.println(message);
        }catch(IOException e){
            throw new RuntimeException("전송오류", e);
        }
    }

    public MessageSender createMessageSender(Socket socket){
        MessageSender messageSender = new MessageSender(socket, this::sendMessage);
        messageSender.start();
        return messageSender;
    }

    public void getMessage(Socket socket){
        try{
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            System.out.println("클라이언트가 서버로부터 " + dataInputStream.readUTF() + "를 받았습니다.");
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public MessageReceiver createMessageReceiver(Socket socket){
        MessageReceiver messageReceiver = new MessageReceiver(socket, this::getMessage);
        messageReceiver.start();
        return messageReceiver;
    }

}
