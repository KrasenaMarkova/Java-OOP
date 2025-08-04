package exampreparation.harpoonDiver.models.diver;

import exampreparation.harpoonDiver.models.seaCatch.SeaCatch;

public interface Diver {
    String getName();

    double getOxygen();

    boolean canDive();

    SeaCatch getSeaCatch();


    void shoot();
}
