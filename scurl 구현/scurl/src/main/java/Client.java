import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.hc.core5.http.ParseException;

public class Client {

  String host;
  String requestMessage;
  Socket socket;
  String responseMessage;
  String protocol;
  String redirectrequest;
  int port;
  int redirectCnt = 0;


  public void setRequestMessage(String inputURL) throws IOException, ParseException {
    this.requestMessage = RequestMessage.setRequestMessage(inputURL);
    this.host = URLInformation.setUrlInformation(inputURL).getHost();
    this.protocol = RequestMessage.protocol;
    this.port = RequestMessage.port;
  }

  public void sendMessage() {
    try {
      this.socket = new Socket(host, port);
      OutputStream outputStream = socket.getOutputStream();
      PrintWriter printWriter = new PrintWriter(outputStream, true);
      printWriter.println(requestMessage);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }


  public void getMessage() throws IOException {
    InputStream inputStream = socket.getInputStream();
    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
    BufferedReader bufferReader = new BufferedReader(inputStreamReader);
    List<String> stringStream = bufferReader.lines().collect(Collectors.toList());
    responseMessage = "";
    for (String st : stringStream) {
      if (st.contains("location: ")) {
        StringBuilder sb = new StringBuilder();
        String tmp = st.split(":")[1];
        tmp = tmp.substring(1, tmp.length());
        sb.append("GET " + tmp + " " + protocol.toUpperCase() + "/1.0");
        sb.append("\n" + "Host: " + this.host + "\n");
        redirectrequest = sb.toString();
      }
      this.responseMessage += st;
      this.responseMessage += "\n";
    }
  }

  public void printRequestMessage() throws IOException {
    if (RequestMessage.containsV()) {
      //요청과 응답 같이 보내야 한다면
      System.out.println("==========requestMessage===============");
      System.out.println(requestMessage);
    }

    System.out.println("================responseMeesage=============");
    System.out.println(responseMessage);
    checkRedirect();
  }

  public void checkRedirect() throws IOException {
    if (redirectCnt == 5) {
      return;
    }
    if (redirectrequest != null) {
      requestMessage = redirectrequest;
      redirectCnt++;
      sendMessage();
      getMessage();
      printRequestMessage();
    }
  }
}

