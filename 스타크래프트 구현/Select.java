//내 종족, 상대방 종족 선택 후 각자 유닛 생성하는 클래스(게임 시작 시)

import java.util.*;

public class Select extends Game{
    
    public static void SelectTribe(){

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
           
        System.out.println("종족을 선택하시오(0: Terran, 1: Protos, 2: Zerg)");
    
        int myNum = scanner.nextInt();
    
        try{
            if(myNum != 0 && myNum != 1 && myNum != 2){ // 만약 입력값이 0,1,2가 아니라면
                throw new InputMismatchException();
            }
            else{
                MyUnit = Create.CreateUnit(myNum); //내 유닛 생성
            }
        }
        catch(InputMismatchException e){
            System.out.println("잘못된 입력입니다.");
            e.printStackTrace();
        }
            
        EnemyUnit = Create.CreateUnit(random.nextInt(3)); //상대방 유닛 생성
    
    }
}
