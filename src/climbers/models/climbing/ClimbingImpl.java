package climbers.models.climbing;

import climbers.models.climber.Climber;
import climbers.models.mountain.Mountain;

import java.util.Collection;

public class ClimbingImpl implements Climbing{
    @Override
    public void conqueringPeaks(Mountain mountain, Collection<Climber> climbers) {
        // колекция, в която да запазваме покорените върхове
        Collection<String> peaks = mountain.getPeaksList();

        // обхождаме всички катерачи
        for (Climber climber : climbers) {
            // докато текущият катечат може да се катери и има върхове
            while (climber.canClimb() && peaks.iterator().hasNext()) {
                // катерачът изкачва върха
                climber.climb();

                // запазваме текущият връх
                String currentPeak = peaks.iterator().next();
                // на текущият алпинист извикваме списъкът с покорените върхове и добавяме текущият връх
                climber.getRoster().getPeaks().add(currentPeak);
                // премахваме от списъка покореният връх
                peaks.remove(currentPeak);
            }

        }
/*        Here is how the conqueringPeaks method works:
•	Climbers cannot climb peaks if their strength is equal to or below 0.
•	They start climbing and conquering peaks, one by one.
•	If they conquer a peak, they add it to their roster of conquered peaks and their strength is decreased.
•	The conquered peak should then be removed from the peaks list of the current mountain.
•	Climbers cannot continue climbing if their strength drops to 0.
        o	If their strength drops to 0, the next climber starts climbing.*/
    }
}
