public class UnFlyUnit extends Unit implements IUnFly{
      
    public UnFlyUnit(String name, Tribe tribe, int health, int offence_power){       
        super(name, health, offence_power);
        this.tribe = tribe;
    }

    public boolean Attack(Unit unit){
        if(unit instanceof IFly){
            return false;
        }
        else{
            unit.Attacked(this.offence_power);
            return true;
        }
    }
}
