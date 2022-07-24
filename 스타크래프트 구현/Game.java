import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Game {

    public static int my_unit;
    public static int your_unit;

    public static Unit your_team[];
    public static Unit my_team[];
 
    public static int your_team_kill_cnt = 0; //상대방 팀원이 죽는 개수
    public static int my_team_kill_cnt = 0; //내 팀원이 죽는 개수

    public static int max_your_team_kill_cnt = 0; //상대방 팀원 개수
    public static int max_team_kill_cnt = 0; //내 팀원이 개수

    /*
    static public int Attacking(Unit unit1, Unit unit2){ //공격하는 유닛, 공격당하는 유닛
        int unit1_offence_power = unit1.getOffencePower(); //공격하는 유닛의 공격력
//        int unit2_health = unit2.getHealth(); //공격당하는 유닛의 체력
        
        unit2.Attack(unit2, unit1_offence_power); //공격함

        return unit2.getHealth();
    }*/


    static public int Attacking(int unit1, int unit2, boolean attack){ //공격하는 유닛, 공격당하는 유닛 //attack == true -> 내 팀이 공격 // attack == false -> 상대팀이 공격

        if(attack){
            int unit1_offence_power = my_team[unit1].getOffencePower(); //공격하는 유닛의 공격력
            //        int unit2_health = unit2.getHealth(); //공격당하는 유닛의 체력
                    
            your_team[unit2].Attack(your_team[unit2], unit1_offence_power); //공격함
            
            return your_team[unit2].getHealth();

        }
        else{
            int unit2_offence_power = your_team[unit2].getOffencePower(); //공격하는 유닛의 공격력
            //        int unit2_health = unit2.getHealth(); //공격당하는 유닛의 체력
                    
            my_team[unit1].Attack(my_team[unit1], unit2_offence_power); //공격함
            
            return my_team[unit1].getHealth();

        }
     
    }


    static public boolean Fly(Unit unit){
        if(unit.flyable == true){
            return true;
        }
        else{
            return false;
        }
    }

    static public boolean Weapon(Unit unit){
        if(unit.weapon == true){
            return true;
        }
        else{
            return false;
        }
    }


    static Random random = new Random();
  
  

    static Unit[] terraunit = { new Terran("marine",tribe.Terran, 10, 3, true, false),
    new Terran("tank",tribe.Terran, 10, 7, false, false),
    new Terran("goliath",tribe.Terran, 15, 5, false, true),
    new Terran("wraith",tribe.Terran, 10, 3, true, false), 
    new Terran("vlkyrie",tribe.Terran, 12, 4, true, false),
    new Terran("BattleCruzer",tribe.Terran, 30, 20, true, false),
};


    static Unit[] protosunit= { new Protos("Zealot",tribe.Protos, 20, 5, false, false), 
    new Protos("Dragoon",tribe.Protos, 15, 3, false, true), 
    new Protos("HighTempler",tribe.Protos, 2, 10, false, false), 
    new Protos("Scout",tribe.Protos, 10, 5, true, false), 
    new Protos("Consair",tribe.Protos, 12, 4, true, false),
    new Protos("Carrier",tribe.Protos, 40, 25, true, false), 
};
    
    static Unit[] zergunit= { new Zerg("Zergling",tribe.Zerg, 2, 2, false, false), 
    new Zerg("Hydralisk",tribe.Zerg, 7, 3, false, true), 
    new Zerg("Ultralisk",tribe.Zerg, 15, 5, false, false), 
    new Zerg("Mutalisk",tribe.Zerg, 8, 2, true, false), 
    new Zerg("Guardain",tribe.Zerg, 6, 3, true, false),
    new Zerg("Queen",tribe.Zerg, 25, 15, true, false), 
};
    

    public static void Select_Animal(int num, String team){
        if(team == "your"){
            switch(num){
                case 0:
//                    System.out.println("적군 : Terran");
                    your_team = new Terran[5];
                    max_your_team_kill_cnt = 5;
                    for(int i=0; i<5; i++){
                        your_team[i] = terraunit[random.nextInt(5)];
                    }
                    break;
                case 1:
//                    System.out.println("적군 : Protos");
                    your_team = new Protos[4];
                    max_your_team_kill_cnt = 4;
                    for(int i=0; i<4; i++){
                        your_team[i] = protosunit[random.nextInt(5)];                        
                    }
                    break;
                case 2:
//                    System.out.println("적군 : Zerg");
                    your_team = new Zerg[8];
                    max_your_team_kill_cnt = 8;
                    for(int i=0; i<8; i++){
                        your_team[i] = zergunit[random.nextInt(5)];
                    }
                    break;
             }
        }
        else{
            switch(num){
                case 0:
//                    System.out.println("아군 : Terran");
                    my_team = new Terran[5];
                    max_team_kill_cnt = 5;
                    for(int i=0; i<5; i++){
                        my_team[i] = terraunit[random.nextInt(5)];
                    }
                    break;
                case 1:
//                    System.out.println("아군 : Protos");
                    my_team = new Terran[4];
                    max_team_kill_cnt = 4;

                    for(int i=0; i<4; i++){
                        my_team[i] = protosunit[random.nextInt(5)];
                    }
                    break;
                case 2:
//                    System.out.println("아군 : Zerg");

                    my_team = new Terran[8];
                    max_team_kill_cnt = 8;
                    for(int i=0; i<8; i++){
                        my_team[i] = zergunit[random.nextInt(5)];
                    }
                    break;
             }
        }
        
    }

    public static void Print_your_team(int enemy_num){

        switch(enemy_num){
            case 0:
                System.out.println("적군 : Terran");
                break;
            case 1:
                System.out.println("적군 : Terran");
                break;
            case 2:
                System.out.println("적군 : Terran");
                break;
        }

        for(int i=0; i<your_team.length; i++){
            if(your_team[i] != null){
            System.out.print(i + ": ");
            System.out.print(your_team[i].getName());
            System.out.print("(현재 방어력: ");
            System.out.print(your_team[i].getHealth() + "  // ");
            System.out.print("공격력: ");
            System.out.println(your_team[i].getOffencePower() + ") ");
            }
        }  
    }

    public static void Print_my_team(int num){

        switch(num){
            case 0:
                System.out.println("아군 : Terran");
                break;
            case 1:
                System.out.println("아군 : Terran");
                break;
            case 2:
                System.out.println("아군 : Terran");
                break;
        }
//        System.out.println("아군 : ");

        for(int i=0; i<my_team.length; i++){
            if(my_team[i] != null){
            System.out.print(i + ": ");
            System.out.print(my_team[i].getName());
            System.out.print("(현재 방어력: ");
            System.out.print(my_team[i].getHealth() + "  // ");
            System.out.print("공격력: ");
            System.out.println(my_team[i].getOffencePower() + ") ");
            }
        }  
    }
    
    public static void main(String[] args){


        System.out.println("0,1,2 중 하나를 선택하세요.");
        System.out.println("0 : Terran // 1: Protos // 2. Zerg");
                
        int enemy_num = random.nextInt(3);

        Scanner scanner = new Scanner(System.in);

        Select_Animal(enemy_num, "your"); //상대팀 팀 구하기(랜덤)

        int num = scanner.nextInt(); //우리 팀 구하기

        try{
            if(num != 0 && num != 1 && num != 2){
                throw new InputMismatchException();
            }
            else{
                Select_Animal(num, "my");
            }            
        }
        catch(InputMismatchException e){
            System.out.println("잘못된 입력입니다.");
            e.printStackTrace();
        }    

        Print_your_team(enemy_num);
        Print_my_team(num);

       
/* 
        for(int i=0; i<your_team.length; i++){
            System.out.println(your_team[i].getName());
        }
        */

        while(true){
            System.out.println("공격을 수행할 아군 유닛과 적군 유닛을 선택하세요: ");
            my_unit = scanner.nextInt();
            your_unit = scanner.nextInt();
    
        //    System.out.println(my_team[my_unit].getName());
        //    System.out.println(your_team[your_unit].getName());
    
    
            boolean attack = true; //공격할 수 있는지 여부
    
            if(!Fly(my_team[my_unit])){ // 내 편은 날 수 없고,
    //            System.out.println("Fly 상속 안 받음");
                
                if(Fly(your_team[your_unit])){ //상대편은 날 수 있고
                    if(!Weapon(my_team[my_unit])){ // 내 편은 무기가 없다면
                        attack = false; // 공격 할 수 없음
                        System.out.println("공격할 수 없습니다. 다시 입력하세요.");
                        continue;
                    } 
                }
            }
    
            if(attack){
                if(Attacking(my_unit,your_unit, true) < 0){ //우리 팀이 공격할 떄
                    System.out.println("공격당하였습니다.");
                    your_team[your_unit] = null;
                    your_team_kill_cnt += 1;
                    if(your_team_kill_cnt == max_your_team_kill_cnt){
                        System.out.println("승리했습니다");
                        break;
                    }
                }
            }
    
            Print_your_team(enemy_num);
            Print_my_team(num);

          

            while(true){
                my_unit = random.nextInt(my_team.length);
                your_unit = random.nextInt(your_team.length);

                if(!Fly(my_team[my_unit])){
                        
                    if(Fly(your_team[your_unit])){ //내 편은 날 수 없고, 상대 편은 날 수 있을 때
                        attack = false;
                        continue;
                    }
                }

                if(attack){
                    if(Attacking(my_unit,your_unit, false) < 0){ //상대팀이 공격할 때
                        System.out.println("공격당하였습니다.");
                        my_team[my_unit] = null;
                        my_team_kill_cnt += 1;
                        if(my_team_kill_cnt == max_team_kill_cnt){
                            System.out.println("승리했습니다");
                            break;
                        }
                    }
                }
            }

             Print_your_team(enemy_num);
            Print_my_team(num);


                        /* 
            System.out.println("상대편 유닛 : ");
            System.out.println(your_team.length);
            for(int i=0; i<your_team.length; i++){
                if(your_team[i] != null){
                    System.out.print(your_team[i].getName());
                    System.out.print("(현재 방어력: ");
                    System.out.println(your_team[i].getHealth() + ")");
                }
            }
            */
        }        
    }
}
