import socket2.MultiClientServer;

class MultiClientServerTest {

    public static void main(String[] args) {
        MultiClientServer multiClientServer = new MultiClientServer();
        new Thread(multiClientServer).start();
    }

}