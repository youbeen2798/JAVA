public abstract class Unit {
    protected int health; //체력
    protected Tribe tribe; //종족
    protected int offence_power; // 공격력
    protected String name; // 이름

    public Unit(String name, int health, int offence_power){
        this.name = name;
        this.health = health;
        this.offence_power = offence_power;
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

    public void Attacked(int attackedPower){
        this.health -= attackedPower;
    }

    public abstract boolean Attack(Unit unit);

}
