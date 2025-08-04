package exampreparation.climbers.models.climbing;

import climbers.models.climber.Climber;
import exampreparation.climbers.models.mountain.Mountain;

import java.util.Collection;

public interface Climbing {
    void conqueringPeaks(Mountain mountain, Collection<Climber> climbers);

}
