
import java.lang.Thread;
import java.util.*;

import printSomething.PaymentMenu;
import printSomething.PrintBeverageMenu;

class Test{

    static List<Beverage> myBeverages = new ArrayList<>(); //음료를 담을 리스트(음료가 2개 이상일 경우를 위해)
    public static Beverage myBeverage;

    public static void main(String[] args) throws InterruptedException{
    
    PrintBeverageMenu.PrintBeverageMenu(); //음료 메뉴리스트 출력
    Select.SelectBeverage(); // 음료 고르기    
    
    PaymentMenu.PrintPaymentMenu();
    Select.selectPayment();
    
    for(Beverage myBeverage: myBeverages){
        if(GetCup.Getcup(myBeverage) == 1){ //컵을 받기
            GetBeverage.getBeverage(myBeverage); //음료 받기
            Thread.sleep(2000); //음료 추출하는 시간(2초)
            System.out.println(myBeverage.getName() + "이 나왔습니다.");
            Thread.sleep(1000); //음료컵 꺼내는 시간(1초)    
        }
    }
    

    }
}