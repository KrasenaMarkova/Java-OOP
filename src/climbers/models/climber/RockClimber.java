package climbers.models.climber;

public class RockClimber extends BaseClimber{

    public RockClimber(String name) {
        super(name, 120);
    }

    @Override
    public void climb() {
        setStrength(this.getStrength() - 60);
        if (getStrength() < 0) {
            setStrength(0);
        }
    }
}
