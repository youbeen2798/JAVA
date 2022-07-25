public class HybridUnit extends Unit implements IHybrid{
    
    public HybridUnit(String name, Tribe tribe, int health, int offence_power){       
        super(name,health, offence_power);
        this.tribe = tribe;
    }

    public boolean Attack(Unit unit){ //날지 못하지만 무기를 가진 유닛
        unit.Attacked(this.offence_power);
        return true;       
    }
}
