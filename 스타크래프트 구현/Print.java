//각 유닛들의 방어력, 공격력을 출력해주는 클래스

public class Print extends Game{

    public static void PrintUnit(Unit unit[]){
        
        if(unit == MyUnit){
            System.out.print("아군: ");
        }
        else{
            System.out.print("적군: ");
        }

        System.out.println(unit[0].tribe); // 종족
        
        for(int i=0; i<unit.length; i++){
            System.out.print( i + ". ");
            System.out.print( unit[i].getName());
            System.out.print("(현재 방어력: " + unit[i].getHealth());
            System.out.println(" // 공격력: " + unit[i].offence_power +  ")");
        }
    }

}
