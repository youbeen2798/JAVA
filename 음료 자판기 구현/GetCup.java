import java.util.*;

public class GetCup {
    public static int Getcup(Beverage beverage){
        Scanner scanner = new Scanner(System.in);

        if(beverage instanceof HotBeverage){ //핫 음료라면
            System.out.println(beverage.getName() + "을 담을 종이컵이 나옵니다.");
            System.out.println("종이컵을 받고 버튼 1을 클릭해주세요.");
            return scanner.nextInt();
        }
        else if(beverage instanceof IceBeverage){ //아이스 음료라면
            System.out.println(beverage.getName() + "을 담을 플라스틱컵이 나옵니다.");
            System.out.println("플라스틱컵을 받고 버튼 1을 클릭해주세요.");

            if(scanner.nextInt() == 1){
                return GetIce.getIce(); //얼음 받기
            }
        }
        return 0;
    }
}
