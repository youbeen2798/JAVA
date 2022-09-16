import socket2.MultiClient;

class MultiClientTest {

    public static void main(String[] args) {
        MultiClient multiClient = new MultiClient();
        new Thread(multiClient).start();
    }
}