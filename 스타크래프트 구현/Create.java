//종족 선택 후, 실제 유닛들(Marine, Hydralisk..)을 생성해주는 클래스

import java.util.Random;

public class Create{
 
    public static Unit[] CreateUnit(int num){
        Random random = new Random();
        Unit[] unit = null;

        switch(num){
            case 0: //Terran을 선택했을 때
                unit = new Unit[5];
                for(int i = 0; i<5; i++){
                    unit[i] = Terran(random.nextInt(5));    
                }
                break;

            case 1: //Protos를 선택했을 때
                unit = new Unit[4];
                for(int i = 0; i<4; i++){
                    unit[i] = Protos(random.nextInt(5));    
                }
                break;
    
            case 2: //Zerg를 선택했을 때
                unit = new Unit[8];
                for(int i = 0; i<8; i++){
                    unit[i] = Zerg(random.nextInt(5));
                }
                break;
        }

        return unit;
    }

    private static Unit Terran(int num){ 

        switch(num){
            case 0:
                return new UnFlyUnit("Marine", Tribe.Terran, 10, 3);                
            case 1:
                return new UnFlyUnit("Tank", Tribe.Terran, 15, 7);
            case 2:
                return new HybridUnit("Goliath", Tribe.Terran, 15, 5);
            case 3:
                return new FlyUnit("Wraith", Tribe.Terran, 10, 3);
            case 4:
                return new FlyUnit("Valkyrie", Tribe.Terran, 12, 4);
            case 5:
                return new FlyUnit("BattleCruzer", Tribe.Terran, 30, 20);
            default:
                return null;
        }
    }

    private static Unit Protos(int num){ 

        switch(num){
            case 0:
                return new UnFlyUnit("Zealot", Tribe.Protos, 20, 3);

            case 1:
                return new UnFlyUnit("Dragoon", Tribe.Protos, 15, 3);

            case 2:
                return new HybridUnit("HighTempler", Tribe.Protos, 2, 10);

            case 3:
                return new FlyUnit("Scout", Tribe.Protos, 10, 5);

            case 4:
                return new FlyUnit("Corsair", Tribe.Protos, 12, 4);

            case 5:
                return new FlyUnit("Carrier", Tribe.Protos, 40, 25);

            default:
                return null;
        }
    }

    private static Unit Zerg(int num){ 

        switch(num){
            case 0:
                return new UnFlyUnit("Zergling", Tribe.Zerg, 2, 2);

            case 1:
                return new HybridUnit("Hydralisk", Tribe.Zerg, 7, 3);

            case 2:
                return new UnFlyUnit("Ultralisk", Tribe.Zerg, 15, 5);

            case 3:
                return new FlyUnit("Mutalisk", Tribe.Zerg, 8, 2);

            case 4:
                return new FlyUnit("Guardian", Tribe.Zerg, 6, 3);

            case 5:
                return new FlyUnit("Queen", Tribe.Zerg, 25, 15);

            default:
                return null;
        }
    }


}

