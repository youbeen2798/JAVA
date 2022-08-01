
import java.lang.Thread;
import java.util.*;

import payment.CreatePayCount;

public interface PaymentMethod {
      public boolean pay(int price, int instanceNumber);
}

class getAccountNumber{
    public static String getaccountNumber(int instanceNumber){
        return CreatePayCount.getPayCountNum(instanceNumber).createAccountNumber(instanceNumber);
    }
}

class Cash extends Test implements PaymentMethod{
    
    public boolean pay(int price, int instanceNumber){

        Integer InputCash = 0; //지불한 값
        Scanner scanner = new Scanner(System.in);

        System.out.print("결제 방법: 현금");
        System.out.println("( 오늘 " + getAccountNumber.getaccountNumber(0) + "번째 현금 결제입니다.)");
        System.out.println("현금을 넣어주십시오.");

        while(true){
            InputCash += scanner.nextInt(); //입력 받기
            if(InputCash.compareTo(price) >=0){ //만약 음료수 값 이상으로 돈을 지불했을 때
                int changeMoney = InputCash - price; //거스름돈
                if(changeMoney > 0){ //거스름돈을 거슬러줘야 한다면
                    change.Change(changeMoney);
                }
                return true;
            }
            else{
                System.out.println("현금이 부족합니다.");
                System.out.println("현금을 더 넣어주시기 바랍니다.");
            }
        }              
    }
}

class Card extends Test implements PaymentMethod {

        public boolean pay(int price, int instanceNumber){
   
        System.out.println("결제 방법: 신용카드");
        System.out.println("( 오늘 " + getAccountNumber.getaccountNumber(1) + "번째 카드 결제입니다.)");

        System.out.println("신용카드로 " + price  + "원 결제중..");
        try {
            Thread.sleep(2000); //결제하는 시간(2초)
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        return true;
    }
}

class OnlinePay extends Test implements PaymentMethod{

    public boolean pay(int price, int instanceNumber){

        System.out.println("결제 방법: 온라인페이");
        System.out.println("( 오늘 " + getAccountNumber.getaccountNumber(2) + "번째 온라인페이 결제입니다.)");
        System.out.println("온라인페이로 " + price  + "원 결제중.."); //결제하는 시간(2초)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        return true;
    }
}
