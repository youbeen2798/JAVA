import java.io.IOException;
import java.util.Scanner;
import org.apache.hc.core5.http.ParseException;

public class ClientMain {

  public static void main(String[] args) throws IOException, ParseException {

    Scanner scanner = new Scanner(System.in);
    String inputMessage = scanner.nextLine();

    Client client = new Client();
    client.setRequestMessage(inputMessage);
    client.sendMessage();
    client.getMessage();
    client.printRequestMessage();

  }

}
