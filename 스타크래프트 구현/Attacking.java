import java.util.Scanner;
import java.util.Random;

public class Attacking extends Game{
    
    public static void Attack(Unit[] unit){
        
        if(unit == EnemyUnit){ // 내가 상대방을 유닛을 공격하는 경우

            Scanner scanner = new Scanner(System.in);

            while(true){

                //만약 내 편에는 날 수 없는 유닛만 존재하지만, 상대편은 날 수 있는 유닛만 존재하여 무한루프에 빠지지 않기 위한 코드 시작
                int EnemyFlyUnitNum = 0;
                int MyUnFlyUnitNum = 0;

                for(int j=0; j<MyUnit.length; j++){
                    if(MyUnit[j] instanceof UnFlyUnit){
                        MyUnFlyUnitNum++;
                    }
                }

                if( MyUnFlyUnitNum == MyUnit.length){  // 나의 유닛 모두가 날지 않는 다면
                    for(int j =0 ; j < EnemyUnit.length; j++){
                        if(EnemyUnit[j] instanceof FlyUnit){
                            EnemyFlyUnitNum++;
                        }
                    }

                    if(EnemyFlyUnitNum == EnemyUnit.length){ //상대방 유닛 모두가 난다면
                        System.out.println("컴퓨터가 게임을 승리하였습니다.");
                        System.exit(0);
                    }
                } //만약 내 편에는 날 수 없는 유닛만 존재하지만, 상대편은 날 수 있는 유닛만 존재하여 무한루프에 빠지지 않기 위한 코드 끝


                System.out.println("공격할 유닛의 번호와 공격당할 유닛의 번호를 입력하시오.");

                int num1 = scanner.nextInt(); //공격할 유닛의 번호
                int num2 = scanner.nextInt(); //공격당할 유닛의 번호
    
                if(num1 >= MyUnit.length || num2 >= EnemyUnit.length){
                    System.out.println("공격할 수 없습니다. 다시 입력하세요.");
                    continue;
                }

                if(!MyUnit[num1].Attack(EnemyUnit[num2])){ //날 수 없는 유닛이 날 수 있는 유닛을 공격한 경우
                    System.out.println("공격할 수 없습니다. 다시 입력하세요.");
                    continue;
                }

                if(EnemyUnit[num2].getHealth() <= 0){
                    EnemyUnit[num2] = null;

                    EnemyUnit = Delete.DeleteNull(EnemyUnit); //널 값은 배열에서 즉시 제거

                    if(EnemyUnit.length == 0){
                        System.out.println("게임을 승리하였습니다.");
                        System.exit(0);
                    }
                }
                break;
            }    
        }   

        else{ //상대방이 내 유닛을 공격하는 경우

            while(true){
                Random random = new Random();

                int num1, num2; //공격할 유닛의 번호, 공격당할 유닛의 번호

                int EnemyUnFlyUnitNum = 0; //만약 내 편에는 날 수 없는 유닛만 존재하지만, 상대편은 날 수 있는 유닛만 존재한다면 무한루프에 빠지게 됨
                int MyFlyUnitNum = 0;

                //만약 내 편에는 날 수 없는 유닛만 존재하지만, 상대편은 날 수 있는 유닛만 존재하여 무한루프에 빠지지 않기 위한 코드 시작
                for(int j=0; j<EnemyUnit.length; j++){
                    if(EnemyUnit[j] instanceof UnFlyUnit){
                        EnemyUnFlyUnitNum++;
                    }
                }

                if( EnemyUnFlyUnitNum == EnemyUnit.length){  // 상대편의 유닛 모두가 난다면
                    for(int j =0 ; j < MyUnit.length; j++){
                        if(MyUnit[j] instanceof FlyUnit){
                            MyFlyUnitNum++;
                        }
                    }
                    if(MyFlyUnitNum == MyUnit.length){
                        System.out.println("사용자가 게임을 승리하였습니다."); //컴퓨터는 모두 날 수 없는 유닛, 사용자는 모두 날 수 있는 유닛이라면
                        System.exit(0);
                    }
                }
                //만약 내 편에는 날 수 없는 유닛만 존재하지만, 상대편은 날 수 있는 유닛만 존재하여 무한루프에 빠지지 않기 위한 코드 끝



                if(EnemyUnit.length == 1){ //java.lang.IllegalArgumentException: bound must be positive 에러 대비 ( //random의 bound 값은 항상 양수여야 함)
                    num1 = 0;
                }
                else{
                    num1 = random.nextInt(EnemyUnit.length - 1); //공격할 유닛의 번호
                }

                if(MyUnit.length == 1){  //java.lang.IllegalArgumentException: bound must be positive 에러 대비 ( //random의 bound 값은 항상 양수여야 함)
                    num2 = 0;
                }
                else{
                    num2 = random.nextInt(MyUnit.length - 1); //공격당할 유닛의 번호
                }

                
                if(!EnemyUnit[num1].Attack(MyUnit[num2])){ //날 수 없는 유닛이 날 수 있는 유닛을 공격한 경우
                    System.out.println("공격에 실패하였습니다.");        
                    continue;
                }

                if(MyUnit[num2].getHealth() <= 0){
                    MyUnit[num2] = null;
                    MyUnit = Delete.DeleteNull(MyUnit); //널 값은 배열에서 즉시 제거

                    if(MyUnit.length == 0){
                        System.out.println("컴퓨터가 게임을 승리하였습니다.");
                        System.exit(0);
                    }
                }
                break;
            }
        }

    }
}
