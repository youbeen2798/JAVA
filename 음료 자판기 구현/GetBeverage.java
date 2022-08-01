import java.lang.Thread;
import ingredient.IUseChocolate;
import ingredient.IUseCoffee;
import ingredient.IUseMilk;
import ingredient.IUsePeach;

public class GetBeverage {

    public static void getBeverage(Beverage beverage){

        System.out.println(beverage.getName() + " 음료을 시작합니다...");
        
        if(beverage instanceof IUseCoffee){ //커피를 사용한다면
            System.out.println("커피를 추출중입니다.");
            try {
                Thread.sleep(2000); //음료 추출하는 시간(2초)
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }

        if(beverage instanceof IUseMilk){ //우유를 사용한다면
            System.out.println("우유를 추출중입니다.");
            try {
                Thread.sleep(2000); //음료 추출하는 시간(2초)
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }

        if(beverage instanceof IUseChocolate){ //초콜릿 시럽을 사용한다면
            System.out.println("초콜릿 시럽을 넣는 중입니다.");
            try {
                Thread.sleep(2000); //음료 추출하는 시간(2초)
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }

        if(beverage instanceof IUsePeach){ //복숭아 시럽을 사용한다면
            System.out.println("복숭아 시럽을 넣는 중입니다.");
            try {
                Thread.sleep(2000); //음료 추출하는 시간(2초)
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
        }

            
        System.out.println("잠시만 기다려주십시오.");

    }
}
