package socket2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MultiClientServer implements Runnable {

    private static int number = 0;
    private Map<Integer, Socket> clientCollection = new HashMap<>();

    //서브 클래스를 만드는 이유는 여러 클라이언트마다 고유의 소켓을 생성하기 위해서이다.
    class subClientServer extends Thread {
        private Socket socket;

        subClientServer(Socket socket) {
            this.socket = socket;
            this.start();
        }

        @Override
        public void run() {
                while(!Thread.currentThread().isInterrupted()){
                    String getMessage = getMessage(socket);
                    sendMessage(getMessage);
                }

        }
    }


    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(1522)) {
            System.out.println("서버가 기다리는중");

            while (true) {
                Socket socket = connect(serverSocket);
                clientCollection.put(++number, socket);
                new subClientServer(socket); //여러개 만들어짐
            }
        } catch (IOException e) {
            throw new RuntimeException("연결 오류", e);
        }
    }

    public Socket connect(ServerSocket serverSocket) {
        try {
            System.out.println("서버와 클라이언트가 연결되었습니다.");
            Socket socket = serverSocket.accept();
            return socket;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(inputStreamReader);

            String inputMessage = br.readLine();
            System.out.println("서버가 클라이언트로부터 " + inputMessage + "를 받았습니다.");
            return inputMessage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        try {
            //생성된 모든 클라이언트에게 하나의 클라이언트로부터 받은 메시지를 뿌려준다.
            for (Map.Entry<Integer, Socket> entrySet : clientCollection.entrySet()) {
                Socket socket = entrySet.getValue();

                OutputStream outputStream = socket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                dataOutputStream.writeUTF(message);
                System.out.println("서버가 클라이언트에게 " + message + "를 보냈습니다.");
            }
        } catch (IOException e) {
            throw new RuntimeException("전송오류", e);
        }
    }
}
