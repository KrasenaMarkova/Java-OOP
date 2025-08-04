package goldDigger.models.operation;

import goldDigger.models.discoverer.Discoverer;
import goldDigger.models.spot.Spot;

import java.util.Collection;

public class OperationImpl implements Operation{
    private Collection<Discoverer> discoverers;

    @Override
    public void startOperation(Spot spot, Collection<Discoverer> discoverers) {
        Collection<String> spotExhibits = spot.getExhibits();
        for (Discoverer discoverer : discoverers) {
            // discoverer може да копае
            // когато имаме експонати
            while (discoverer.canDig() && spotExhibits.iterator().hasNext()) {
                // discoverer копае
                discoverer.dig();
                // текущ експонат
                String currentExhibits = spotExhibits.iterator().next();
                // към текущият discoverer добамя текущия експонат
                discoverer.getMuseum().getExhibits().add(currentExhibits);
                // премахваме текущият експонат от колекцията с експонати
                spotExhibits.remove(currentExhibits);
            }
        }
    }
}
