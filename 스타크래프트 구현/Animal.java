public class Animal extends Unit{
    
    public Animal(String name, tribe tribe,int health,  int offence_power, boolean flyable, boolean haveweapon){
        super(name, tribe, health, offence_power, flyable, haveweapon);
    }

    /* 
    public boolean Fly(Animal animal){
        if(animal.flyable == true){
            return true;
        }
        else{
            return false;
        }
    }*/


    public boolean Weapon(Animal animal){
        if(animal.weapon == true){
            return true;
        }
        else{
            return false;
        }
    }
}



class Terran extends Animal{

    public Terran(String name, tribe tribe, int health, int offence_power, boolean flyable, boolean haveweapon) {
        super(name, tribe, health, offence_power, flyable, haveweapon);
    }

    class Marine implements Fly{
        Unit Marine = new Terran("Marine",tribe.Terran, 10, 3, true, false);
    }

    class Tank implements UnFly{
        Unit Tank = new Terran("Tank",tribe.Terran, 10, 3, false, false);

    }

    class Goliath implements Hybrid{
        Unit Goliath = new Terran("Goliath",tribe.Terran, 10, 3, false, true);
    }

    class Wratih implements Fly{
        Unit Wratih = new Terran("Wratih",tribe.Terran, 10, 3, true, false);
    }

    class Vlkyrie implements Fly{
        Unit Vlkyrie = new Terran("vlkyrie",tribe.Terran, 10, 3, true, false);
    }

    class BattleCruzer implements Fly{ //종족 추가하기
        Unit Vlkyrie = new Terran("BattleCruzer",tribe.Terran, 30, 20, true, false);
    }

}


class Protos extends Animal{

    public Protos(String name, tribe tribe, int health, int offence_power,  boolean flyable, boolean haveweapon) {
        super(name, tribe, health, offence_power, flyable, haveweapon);
    }

    class Zealot implements UnFly{
        Unit Zealot = new Protos("Zealot",tribe.Protos, 20, 5, false, false);
    }

    class Dragoon implements UnFly{
        Unit  Dragoon = new Protos("Dragoon",tribe.Protos, 15, 3, false, true);
    }

    class HighTempler implements UnFly{
        Unit HighTempler = new Protos("HighTempler",tribe.Protos, 2, 10, false, false);
    }

    class Scout implements Fly{
        Unit Scout = new Protos("Scout",tribe.Protos, 10, 5, true, false);
    }

    class Consair implements Fly{
        Unit Consair = new Protos("Consair",tribe.Protos, 12, 4, true, false);
    }

    class Carrier implements Fly{
        Unit Carrier = new Protos("Carrier",tribe.Protos, 40, 25, true, false);
    }

}

class Zerg extends Animal{

    public Zerg(String name, tribe tribe, int health, int offence_power, boolean flyable, boolean haveweapon) {
        super(name, tribe, health, offence_power, flyable, haveweapon);
    }

    class Zergling implements Fly{
        Unit Zergling = new Zerg("Zergling",tribe.Zerg, 2, 2, true, false);
    }

    class Hydralisk implements Fly{
        Unit Hydralisk = new Zerg("Hydralisk",tribe.Zerg, 7, 3,true, true);
    }
    class Ultralisk implements UnFly{
        Unit Ultralisk = new Zerg("Ultralisk",tribe.Zerg, 15, 5, true, false);
    }
    class Mutalisk implements UnFly{
        Unit Mutalisk = new Zerg("Mutalisk",tribe.Zerg, 8, 2, true, false);
    }
    class Guardain implements UnFly{
        Unit Guardain = new Zerg("Guardain",tribe.Zerg, 6, 3, true, false);
    }

    class Queen implements UnFly{
        Unit Queen = new Zerg("Queen",tribe.Zerg, 15, 25, true, false);
    }
}    