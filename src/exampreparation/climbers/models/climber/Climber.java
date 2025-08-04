package exampreparation.climbers.models.climber;

//import climbers.models.list.Roster;

import exampreparation.climbers.models.list.Roster;

public interface Climber {
    String getName();

    double getStrength();

    boolean canClimb();

    Roster getRoster();


    void climb();
}
