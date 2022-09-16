package socket2;

import java.net.Socket;
import java.util.function.Consumer;

public class MessageReceiver extends Thread{

    private final Consumer<Socket> getMessage;

    private Socket client;

    public MessageReceiver(Socket socket, Consumer<Socket> getMessage){
        this.getMessage = getMessage;
        this.client = socket;
    }

    @Override
    public void run(){
        while(!this.interrupted()){
            getMessage.accept(client);
        }
    }
}
