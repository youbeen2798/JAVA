package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{

    @Override
    public void run(){
        try(ServerSocket serverSocket = new ServerSocket(1522)){
            Socket socket = connect(serverSocket);
            String message = getMessage(socket);
            sendMessage(socket, message);
        }catch(IOException e){
            throw new RuntimeException("연결 오류", e);
        }
    }

    public Socket connect(ServerSocket serverSocket){
        try{
            System.out.println("클라이언트가 서버에 연결을 시도합니다...");
            Thread.sleep(3000);
            Socket socket = serverSocket.accept();
            return socket;
        }catch(InterruptedException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public String getMessage(Socket socket){
        try{
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(inputStreamReader);
            String inputMessage = bufferReader.readLine();

            System.out.println("서버가 클라이언트로부터 받은 메시지: " + inputMessage);

            return inputMessage;
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(Socket socket, String message){
        try{
            System.out.println("서버가 클라이언트에게 받은 메시지를 다시 전달하려 합니다.");
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            Thread.sleep(1000);
            System.out.println("서버가 클라이언트에게 메시지를 보냈습니다.");
            dataOutputStream.writeUTF(message);
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
