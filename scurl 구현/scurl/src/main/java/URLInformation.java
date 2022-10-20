import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URLInformation {

  public static String inputURL;

  public static URL setUrlInformation(String inputMessage) throws MalformedURLException {
    inputURL = inputMessage;
    List<String> list = new ArrayList<String>();
    String[] strArr = inputURL.split(" ");
    URL url = new URL(strArr[strArr.length - 1]);
    return url;
  }

  // -X가 있으면 바로 뒤, -F면 포스트, -d면 포스트,없으면 GET
  public static String getMethod(String[] strArr) {
    String method;
    if (Arrays.asList(strArr).contains("-X")) {
      int index = Arrays.asList(strArr).indexOf("-X") + 1;
      return strArr[index];
    } else {
      if (Arrays.asList(strArr).contains("-F") || Arrays.asList(strArr).contains("-d")) {
        return "POST";
      } else {
        return "GET";
      }
    }
  }
}

