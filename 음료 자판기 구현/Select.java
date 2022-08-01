
import java.util.*;

public class Select extends Test {
    
    public static int TotalPrice = 0; //음료 총 가격

    static Scanner scanner = new Scanner(System.in);

    public static void SelectBeverage(){ //음료 메뉴 선택하는 메소드

        System.out.println("메뉴를 선택해주세요.");
        System.out.println("메뉴를 다 선택하신후 결제 완료버튼(-1번) 버튼을 눌러주세요."); //메뉴 여러 개 결제할 수도 있기 때문
        
        int num = -2;
        while(true){ 
            num = scanner.nextInt(); 
            if(num ==-1){
                break;
            }

            myBeverage = Create.CreateBeverage(num);
            myBeverages.add(myBeverage); // 주문 음료들을 담은 리스트
            TotalPrice += myBeverages.get(myBeverages.size()-1).getPrice(); //음료 총 가격
        }

        if(TotalPrice == 0){ //음료를 선택하지 않고 -1 버튼을 클릭했다면 시스템 종료
            System.exit(0);
        }
        System.out.println("가격은 " + TotalPrice + "원 입니다.");

    }

    public static void selectPayment(){ //지불방법 선택하는 메소드

        System.out.print("결제 방법을 선택해주십시오: ");

        int num = scanner.nextInt();

        try{
            if( 0<= num && num<= 2){
                switch(num){
                    case 0:
                          myBeverage.pay(new Cash(), TotalPrice, num);
                        break;
                    case 1:
                          myBeverage.pay(new Card(), TotalPrice, num);
                        break;
                    case 2:
                           myBeverage.pay(new OnlinePay(),TotalPrice, num);
                        break;
                }
            }
            else{
                throw new InputMismatchException();
            }
        }
        catch(InputMismatchException e){
            System.out.println("잘못된 입력입니다.");
            e.printStackTrace();
        }


    }
}
