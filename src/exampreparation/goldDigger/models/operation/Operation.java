package exampreparation.goldDigger.models.operation;

import exampreparation.goldDigger.models.disocverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public interface Operation {
    void startOperation(Spot spot, Collection<Discoverer> discoverers);

}
