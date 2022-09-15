import socket.Client;
import socket.Server;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();
        Server server = new Server();

        new Thread(server).start();
        new Thread(client).start();
    }
}
