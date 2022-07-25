public class Game {

    public static Unit[] MyUnit; //아군
    public static Unit[] EnemyUnit; //적군
    public static void main(String[] args){

        Select.SelectTribe(); //종족 선택하기

        Print.PrintUnit(MyUnit); //아군(내 종족 ) 출력
        Print.PrintUnit(EnemyUnit); //적군(상대방 종족) 출력

        while(true){
            Attacking.Attack(EnemyUnit); //내 유닛이 상대방 유닛 공격
            Attacking.Attack(MyUnit); //상대방 유닛(컴퓨터)이 내 유닛 공격
    
            Print.PrintUnit(MyUnit);
            Print.PrintUnit(EnemyUnit);
        }
      
    }
}

