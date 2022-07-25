public class FlyUnit extends Unit implements IFly{
    
    public FlyUnit(String name, Tribe tribe, int health, int offence_power){       
        super(name,health, offence_power);
        this.tribe = tribe;
    }

    public boolean Attack(Unit unit){
        unit.Attacked(this.offence_power);
        return true;       
    }
}
