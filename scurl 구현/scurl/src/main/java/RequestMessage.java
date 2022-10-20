import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;

public class RequestMessage {

  public static List<Integer> H_index;
  public static int F_index;
  public static int D_index;
  public static String[] strArr;
  public static String protocol;
  public static int port;

  //requestMessage를 리턴해주는 메소드
  public static String setRequestMessage(String inputURL) throws IOException, ParseException {

    URL url = URLInformation.setUrlInformation(inputURL);
    strArr = inputURL.split(" ");
    String host = url.getHost();
    String path = url.getPath();
    protocol = url.getProtocol();
    port = (url.getPort() == -1) ? url.getDefaultPort() : url.getPort();
    String method = URLInformation.getMethod(strArr);

    StringBuilder sb = new StringBuilder();
    sb.append(method);
    sb.append(" " + path + " " + "HTTP/1.0" + "\n");
    sb.append("Host: " + host);

    if (containsH()) {
      for (Integer st : H_index) {
        String additionalHeader = strArr[st + 1] + strArr[st + 2];
        additionalHeader = additionalHeader.substring(1, additionalHeader.length() - 1);
        sb.append("\n" + additionalHeader);
      }
    }
    if (containsF()) {
      String FilePath = strArr[F_index + 1].split("=")[1];
      FilePath = FilePath.substring(1, FilePath.length() - 1); //hello.py

      File file = new File(FilePath);
      FileBody fileBody = new FileBody(file,
          ContentType.create(Files.probeContentType(file.toPath())));

      MultipartEntityBuilder builder = MultipartEntityBuilder.create();
      builder.setMode(HttpMultipartMode.LEGACY);
      builder.addPart("uploadfile", fileBody);

      HttpEntity entity = builder.build();

      if (entity != null) {
        InputStreamReader inputStreamReader = new InputStreamReader(entity.getContent());
        String fileEntity = new BufferedReader(inputStreamReader).lines()
            .collect(Collectors.joining("\n"));
        String boundary = fileEntity.split("\n")[0];
        sb.append("\n" + "Content-Length: " + fileEntity.length());
        String contentType = "Content-Type: multipart/form-data";

        sb.append("\n" + contentType + "; boundary=" + boundary + "\n" + "\n" + fileEntity);
      }
    }
    if (containsD()) {
      int startIndex = Arrays.asList(strArr).indexOf("{");
      int endIndex = Arrays.asList(strArr).lastIndexOf("}");

      StringBuilder sb2 = new StringBuilder();
      for (int i = startIndex; i <= endIndex; i++) {
        sb2.append(strArr[i]);
      }
      sb.append("\n");
      sb.append("Content-Length: " + sb2.toString().length());
      sb.append("\n" + "\n" + sb2);
    }
    sb.append("\n");
    return sb.toString();
  }

  public static boolean containsH() {
    H_index = new ArrayList<Integer>();
    int index = 0;
    boolean flag = false;
    for (String st : strArr) {
      if (st.equals("-H")) {
        H_index.add(index);
        flag = true;
      }
      index++;
    }
    return flag;
  }

  public static Boolean containsF() {
    int index = 0;
    for (String st : strArr) {
      if (st.equals("-F")) {
        F_index = index;
        return true;
      }
      index++;
    }
    return false;
  }

  public static Boolean containsD() {
    int index = 0;
    for (String st : strArr) {
      if (st.equals("-d")) {
        D_index = index;
        return true;
      }
      index++;
    }
    return false;
  }

  public static Boolean containsV() {
    for (String st : strArr) {
      if (st.equals("-v")) {
        return true;
      }
    }
    return false;
  }

}
