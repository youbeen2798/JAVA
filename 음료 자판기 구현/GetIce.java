import java.util.*;

public class GetIce {

    public static int getIce(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("얼음이 나옵니다.");
        System.out.println("얼음을 다 받은 후 버튼1을 클릭하세요.");
        return scanner.nextInt();
    }
}
