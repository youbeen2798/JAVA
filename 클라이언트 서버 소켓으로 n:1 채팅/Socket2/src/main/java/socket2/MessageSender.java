package socket2;

import java.net.Socket;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class MessageSender extends Thread{

    private final BiConsumer<Socket, String> sendMessage;

    private final Socket client;

    //메소드를 변수로 저장
    public MessageSender(Socket client, BiConsumer<Socket, String> sendMessage){
        this.client = client;
        this.sendMessage = sendMessage;
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);

        while(!this.interrupted()){
    //        System.out.print("입력할 문자열: ");
            String message = scanner.nextLine();
            sendMessage.accept(client, message);
        }
    }

}
