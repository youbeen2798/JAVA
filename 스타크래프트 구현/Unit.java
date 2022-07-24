public abstract class Unit{
    
    private String name;
    private int health;
    private tribe tribe;
    private int offence_power;
    public boolean flyable;
    public boolean weapon;


    public Unit(String name, tribe tribe, int health, int offence_power, boolean flyable, boolean haveweapon){
        this.name = name;
        this.health = health;
        this.tribe = tribe;
        this.offence_power = offence_power;
        this.flyable = flyable;
        this.weapon = haveweapon;

    }

    public String getName(){
        return this.name;
    }

    public int getHealth(){
        return this.health;
    }

    public int getOffencePower(){
        return offence_power;
    }


    public tribe getTribe(){
        return this.tribe;
    }

    public void Attack(Unit unit, int attacked_power){ //공격당하는 유닛, 공격당하는 파워
        unit.health -= attacked_power;
    }


    /*
    public boolean Weapon(Animal animal){
        if(animal.weapon == true){
            return true;
        }
        else{
            return false;
        }
    }
    */
//    public abstract boolean Fly(Animal animal);

 //   public abstract boolean Weapon(Animal animal);
}
